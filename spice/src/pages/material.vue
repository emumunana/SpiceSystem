<template>
    <div>
        <div class="page-area">
            <div class="title"><i class="el-icon-menu"></i>原材料新增</div>
            <div class="display-flex">
                <div class="display-flex">
                    <div class="form-label">材料编码</div>
                    <el-input v-model="material.materialId" placeholder="材料编号"></el-input>
                </div>
                <div class="display-flex">
                    <div class="form-label">材料名称</div>
                    <el-input v-model="material.materialName" placeholder="材料名称"></el-input>
                </div>
                <div class="display-flex">
                    <div class="form-label" style="width:320px;">材料单价（单位：元/KG）</div>
                    <el-input v-model="material.materialPrice" placeholder="材料单价"></el-input>
                </div>
            </div>
            <div class="display-flex">
                <el-button class="submit-btn" @click="addMeterial" type="primary" icon="el-icon-upload">添加</el-button>
                <el-button type="info" @click="resetForm" icon="el-icon-refresh">重置</el-button>
            </div>
        </div>
        <div class="page-area">
            <div class="title"><i class="el-icon-menu"></i>原材料查询</div>
            <div class="display-flex">
                <div class="display-flex">
                    <div class="form-label">关键字</div>
                    <el-input v-model="searchKey" placeholder="材料编号或材料名称"></el-input>
                </div>
                <div class="display-flex">
                    <el-button class="submit-btn" @click="searchMaterials" type="primary" icon="el-icon-search">查询</el-button>
                </div>
            </div>
            <div class="title"><i class="el-icon-menu"></i>查询结果<label style="font-size:14px;padding-left:20px;color:#b2b2b2;">查获数据{{searchCount}}条</label></div>
            <div class="table-area">
                <el-table :data="materialInfo" style="width: 1110px;border: 1px solid #c2c2c2;" max-height="340">
                    <el-table-column fixed label="" width="10"></el-table-column>
                    <el-table-column  prop="materialId" label="材料编码" width="260"></el-table-column>
                    <el-table-column  prop="materialName" label="材料名称" width="350"></el-table-column>
                    <el-table-column  prop="materialPrice" label="材料单价（单位：元/KG）" width="350"></el-table-column>
                    <el-table-column fixed="right" label="操作" width="150">
                        <template slot-scope="scope">
                            <el-button @click.native.prevent="deleteMeterial(scope.$index)" type="text" size="small">移除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            material: {
                materialId: '', // 原材料ID
                materialName: '', // 原材料名称
                materialPrice: '' // 原材料价格
            }, // 新增或修改的原材料信息
            searchKey: '', // 原材料查询关键字
            oldSearchKey: '', // 上次原材料查询关键字
            searchCount: 0, // 查询记录数
            materialInfo: [] // 查询原材料信息列表
        }
    },
    methods: {
        // 重置表单
        resetForm() {
            this.material = {
                materialId: '', // 原材料ID
                materialName: '', // 原材料名称
                materialPrice: '' // 原材料价格
            }
        },
        // 接口请求，根据关键字查询原材料信息
        searchMaterials() {
            if (this.$util.isNull(this.searchKey)) {
                this.$message.error('关键字不能为空！')
                return false
            }
            if (this.searchKey === this.oldSearchKey) {
                return false
            }
            this.$http.get(this, '/material/search', {keyword: this.searchKey}, res => {
                this.oldSearchKey = this.searchKey
                this.searchCount = res.data.result.length
                this.materialInfo = res.data.result
            })
        },
        // 接口请求，新增原材料信息
        addMeterial() {
            if (this.material.materialId === '' || this.material.materialId === null || this.material.materialId === undefined) {
                this.$message.error('原材料编号不能为空！')
                return
            }
            if (this.material.materialName === '' || this.material.materialName === null || this.material.materialName === undefined) {
                this.$message.error('原材料名称不能为空！')
                return
            }
            if (this.material.materialPrice === '' || this.material.materialPrice === null || this.material.materialPrice === undefined) {
                this.$message.error('原材料价格不能为空！')
                return
            }
            if (!(/^(-?\d+)(\.\d+)?$/.test(this.material.materialPrice))) {
                this.$message.error('原材料价格请输入数字！')
                return
            }
            this.$http.post(this, '/material/add', this.material, res => {
                if (res.data.errCode === this.$http.SUCCESS) {
                    this.material = {
                        materialId: '',
                        materialName: '',
                        materialPrice: ''
                    }
                    this.$message({
                        message: '原材料添加成功！',
                        type: 'success'
                    })
                } else if (res.data.errCode === this.$http.INSERT_FAIL_1) {
                    // 新增过程判断原材料ID已存在，提示是否修改
                    this.$confirm('原材料信息已存在，确认是否修改', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.updateMeterial()
                    }).catch(() => {
                    })
                } else {
                    this.$message.error('原材料添加失败')
                }
            })
        },
        // 接口请求，修改原材料信息
        updateMeterial() {
            this.$http.post(this, '/material/update', this.material, res2 => {
                if (res2.data.errCode === this.$http.SUCCESS) {
                    this.material = {
                        materialId: '',
                        materialName: '',
                        materialPrice: ''
                    }
                    this.$message({
                        type: 'success',
                        message: '原材料修改成功!'
                    })
                } else {
                    this.$message.error('原材料修改失败')
                }
            })
        },
        // 接口请求，删除原材料信息
        deleteMeterial(row) {
            this.$confirm('原材料信息删除后无法恢复，确认执行删除操作？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http.get(this, '/material/delete', {materialId: this.materialInfo[row].materialId}, res => {
                    if (res.data.errCode === this.$http.SUCCESS) {
                        var newList = []
                        for (var index in this.materialInfo) {
                            if (index !== row.toString()) {
                                newList.push(this.materialInfo[index])
                            }
                        }
                        this.materialInfo = newList
                        this.$message({
                            type: 'success',
                            message: '原材料删除成功!'
                        })
                    } else {
                        this.$message.error('原材料删除失败')
                    }
                })
            }).catch(() => {
            })
        }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
