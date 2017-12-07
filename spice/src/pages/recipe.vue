<template>
    <div>
        <div class="page-area">
            <div class="title"><i class="el-icon-menu"></i>配方详情查询</div>
            <div class="display-flex">
                <div class="display-flex">
                    <div class="form-label">配方编号</div>
                    <el-select class="select-width" v-model="searchKey" filterable placeholder="请选择">
                        <el-option v-for="(item, index) in recipeList" :key="item.recipeId" :label="recipeLabel(item)" :value="item.recipeId"></el-option>
                    </el-select>
                </div>
                <div class="display-flex">
                    <el-button class="submit-btn" @click="searchRecipeInfo" type="primary" icon="el-icon-search">查询</el-button>
                    <el-button type="info" @click="resetForm" icon="el-icon-refresh">重置</el-button>
                </div>
            </div>
            <div class="title"><i class="el-icon-menu"></i>配方详情</div>
            <div class="display-flex">
                <div class="form-label">配方编号</div>
                <el-input v-model="recipeId" :disabled="searchFlag" class="input-width" placeholder="配方编号"></el-input>
                <div class="form-label">配方名称</div>
                <el-input v-model="recipeName" class="input-width" placeholder="配方名称"></el-input>
            </div>
            <div class="display-flex">
                <div class="form-label">成本</div>
                <el-input v-model="cost" :disabled="true" class="input-width" ></el-input>
            </div>
            <div class="title">大料添加</div>
            <div class="display-flex" >
                <div class="form-label">材料</div>
                <el-select class="select-width-max" v-model="mainRecipeIndex" filterable placeholder="请选择">
                    <el-option v-for="(item, index) in materialList" :key="item.materialId" :label="materialLabel(item)" :value="index"></el-option>
                </el-select>
            </div>
            <div class="display-flex" >
                <div class="form-label">分量</div>
                <el-input placeholder="请输入内容" v-model="mainRecipeScale" class="input-width"></el-input>
                <el-button @click="addMainRecipe" style="margin-left: 10px;" type="primary" icon="el-icon-circle-plus">添加</el-button>
            </div>
            <div class="table-area">
                <el-table :data="mainRecipeData" style="width: 750px;border: 1px solid #c2c2c2;" >
                    <el-table-column label="大料单" >
                        <el-table-column label="材料" width="340">
                            <template slot-scope="scope">
                                <el-input :disabled="true" :value="materialLabel(mainRecipeData[scope.$index].materialInfo)" style="width:315px" ></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column label="分量（单位：克）" width="300">
                            <template slot-scope="scope">
                                <el-input v-model="mainRecipeData[scope.$index].scale" @input="costCount" placeholder="请输入内容"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="110">
                            <template slot-scope="scope">
                                <el-button type="danger" @click="deleteMainRecipe(scope.$index)" icon="el-icon-remove">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table-column>
                </el-table>
            </div>
            <div class="title">小料添加</div>
            <div class="display-flex" >
                <div class="form-label">材料</div>
                <el-select class="select-width-max" v-model="secondRecipeIndex" filterable placeholder="请选择">
                    <el-option v-for="(item, index) in materialList" :key="item.materialId" :label="materialLabel(item)" :value="index"></el-option>
                </el-select>
            </div>
            <div class="display-flex" >
                <div class="form-label">分量</div>
                <el-input placeholder="请输入内容" v-model="secondRecipeScale" class="input-width"></el-input>
                <el-button @click="addSecondRecipe" style="margin-left: 10px;" type="primary" icon="el-icon-circle-plus">添加</el-button>
            </div>
            <div class="table-area">
                <el-table :data="secondRecipeData" style="width: 750px;border: 1px solid #c2c2c2;" >
                    <el-table-column label="小料单" >
                        <el-table-column label="材料" width="340">
                            <template slot-scope="scope">
                                <el-input :disabled="true" :value="materialLabel(secondRecipeData[scope.$index].materialInfo)" style="width:315px" ></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column label="分量（单位：克）" width="300">
                            <template slot-scope="scope">
                                <el-input v-model="secondRecipeData[scope.$index].scale" @input="costCount" placeholder="请输入内容"></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="110">
                            <template slot-scope="scope">
                                <el-button type="danger" @click="deleteSecondRecipe(scope.$index)" icon="el-icon-remove">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table-column>
                </el-table>
            </div>
            <div>
                <el-button style="margin-top: 10px;" @click="saveRecipe" type="primary" icon="el-icon-upload">保存配方</el-button>
                <el-button style="margin-top: 10px;" @click="deleteRecipe" type="danger" icon="el-icon-delete">删除配方</el-button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            searchKey: '', // 查询的配方ID
            searchFlag: false, // 是否通过查询获得的配方信息，区分新增还是修改操作
            cost: '0  元', // 成本统计
            recipeId: '', // 新增修改的配方ID
            recipeName: '', // 新增修改的配方名称
            mainRecipeData: [
                // {
                //     materialIndex: null, // 材料列表中的下标值
                //     scale: '' // 分量
                // }
            ], // 大料单信息
            secondRecipeData: [
                // {
                //     materialIndex: null, // 材料列表中的下标值
                //     scale: '' // 分量
                // }
            ], // 小料单信息
            mainRecipeIndex: '',
            mainRecipeScale: 0,
            secondRecipeIndex: '',
            secondRecipeScale: 0,
            materialList: [], // 原材料列表
            recipeList: [] // 配方列表
        }
    },
    created() {
        this.getMaterialList()
        this.getRecipeList()
    },
    beforeUpdate() {
        console.log('111======')
    },
    methods: {
        // 成本统计
        costCount() {
            var costTemp = 0
            for (var i = 0; i < this.mainRecipeData.length; i++) {
                if (this.mainRecipeData[i].materialInfo && this.mainRecipeData[i].scale !== '') {
                    for (var j = 0; j < this.materialList.length; j++) {
                        if (this.mainRecipeData[i].materialInfo.materialId === this.materialList[j].materialId) {
                            console.log(Math.round(parseFloat(this.mainRecipeData[i].scale) * parseFloat(this.materialList[j].materialPrice) / 1000 * 100) / 100)
                            costTemp = costTemp + Math.round(parseFloat(this.mainRecipeData[i].scale) * parseFloat(this.materialList[j].materialPrice) / 1000 * 100) / 100
                            console.log('==' + costTemp)
                        }
                    }
                }
            }
            for (var m = 0; m < this.secondRecipeData.length; m++) {
                if (this.secondRecipeData[m].materialInfo && this.secondRecipeData[m].scale !== '') {
                    for (var n = 0; n < this.materialList.length; n++) {
                        if (this.secondRecipeData[m].materialInfo.materialId === this.materialList[n].materialId) {
                            console.log(Math.round(parseFloat(this.secondRecipeData[m].scale) * parseFloat(this.materialList[n].materialPrice) / 1000 * 100) / 100)
                            costTemp = costTemp + Math.round(parseFloat(this.secondRecipeData[m].scale) * parseFloat(this.materialList[n].materialPrice) / 1000 * 100) / 100
                            console.log('==' + costTemp)
                        }
                    }
                }
            }
            this.cost = Math.round(costTemp * 100) / 100 + '  元'
        },
        // 原材料信息格式化
        materialLabel(item) {
            try {
                // console.log(item.materialId)
                return item.materialId + '-' + item.materialName
            } catch (e) {
                console.log(item)
            }
        },
        // 配方信息格式化
        recipeLabel(item) {
            return item.recipeId + '-' + item.recipeName
        },
        // 接口请求，获取配方列表
        getRecipeList() {
            this.$http.get(this, '/recipe/list', {}, res => {
                if (res.data.errCode === this.$http.SUCCESS) {
                    this.recipeList = res.data.result
                } else {
                    this.$message.error('配方信息查询失败！')
                }
            })
        },
        // 接口请求，获取原材料列表
        getMaterialList() {
            this.$http.get(this, '/material/search', {keyword: ''}, res => {
                if (res.data.errCode === this.$http.SUCCESS) {
                    this.materialList = res.data.result
                } else {
                    this.$message.error('原材料信息查询失败！')
                }
            })
        },
        // 接口请求，根据配方ID获取配方详情
        searchRecipeInfo() {
            this.$http.get(this, '/recipe/find', {recipeId: this.searchKey}, res => {
                if (res.data.errCode === this.$http.SUCCESS) {
                    var result = res.data.result
                    this.recipeId = result.recipeId
                    this.recipeName = result.recipeName
                    this.mainRecipeData = result.mainRecipe
                    this.secondRecipeData = result.secondRecipe
                    this.costCount()
                    this.searchFlag = true
                } else {
                    this.$message.error('配方信息查询失败！')
                }
            })
        },
        // UI操作，增加大料单材料输入项
        addMainRecipe() {
            if (this.$util.isNull(this.mainRecipeIndex)) {
                this.$message.error('配方材料信息不能为空！')
                return false
            }
            if (this.$util.isNull(this.mainRecipeScale)) {
                this.$message.error('材料分量不能为空！')
                return
            }
            if (this.$util.isNum(this.mainRecipeScale)) {
                this.$message.error('材料分量请输入数字！')
                return false
            }
            var item = {
                materialInfo: this.materialList[this.mainRecipeIndex],
                scale: this.mainRecipeScale
            }
            this.mainRecipeData.push(item)
            this.mainRecipeIndex = ''
            this.mainRecipeScale = 0
            setTimeout(() => {
                this.costCount()
            }, 800)
        },
        // UI操作，删除大料单材料输入项
        deleteMainRecipe(index) {
            this.mainRecipeData.splice(index, 1)
        },
        // UI操作，增加小料单材料输入项
        addSecondRecipe() {
            if (this.$util.isNull(this.secondRecipeIndex)) {
                this.$message.error('配方材料信息不能为空！')
                return false
            }
            if (this.$util.isNull(this.secondRecipeScale)) {
                this.$message.error('材料分量不能为空！')
                return
            }
            if (this.$util.isNum(this.secondRecipeScale)) {
                this.$message.error('材料分量请输入数字！')
                return false
            }
            var item = {
                materialInfo: this.materialList[this.secondRecipeIndex],
                scale: this.secondRecipeScale
            }
            this.secondRecipeData.push(item)
            this.secondRecipeIndex = ''
            this.secondRecipeScale = 0
            setTimeout(() => {
                this.costCount()
            }, 800)
        },
        // UI操作，删除下料单材料输入项
        deleteSecondRecipe(index) {
            console.log(index)
            this.secondRecipeData.splice(index, 1)
        },
        // 重置页面表单项
        resetForm() {
            this.searchKey = ''
            this.recipeId = ''
            this.recipeName = ''
            this.cost = '0  元'
            this.mainRecipeIndex = ''
            this.mainRecipeScale = 0
            this.secondRecipeIndex = ''
            this.secondRecipeScale = 0
            this.mainRecipeData = []
            this.secondRecipeData = []
            this.searchFlag = false
        },
        // 新增修改配方上送参数初始化
        paramInit() {
            return {
                recipeId: this.recipeId,
                recipeName: this.recipeName,
                mainRecipe: this.mainRecipeData,
                secondRecipe: this.secondRecipeData
            }
        },
        // 检查参数输入完整性
        checkParam() {
            if (this.recipeId === '' || this.recipeId === null || this.recipeId === undefined) {
                this.$message.error('配方编号不能为空！')
                return false
            }
            if (this.recipeName === '' || this.recipeName === null || this.recipeName === undefined) {
                this.$message.error('配方名称不能为空！')
                return false
            }
            if (this.mainRecipeData.length > 0) {
                for (var i = 0; i < this.mainRecipeData.length; i++) {
                    var mic = this.mainRecipeData[i].scale
                    if (mic === '' || mic === null || mic === undefined) {
                        this.$message.error('大料单中材料分量不能为空！')
                        return false
                    }
                    if (!(/^(-?\d+)(\.\d+)?$/.test(this.mainRecipeData[i].scale))) {
                        this.$message.error('大料单中材料分量请输入数字！')
                        return false
                    }
                }
            } else {
                this.$message.error('配方大料单信息不能为空！')
                return false
            }
            if (this.secondRecipeData.length > 0) {
                for (var j = 0; j < this.secondRecipeData.length; j++) {
                    var sic = this.secondRecipeData[j].scale
                    if (sic === '' || sic === null || sic === undefined) {
                        this.$message.error('小料单中材料分量不能为空！')
                        return false
                    }
                    if (!(/^(-?\d+)(\.\d+)?$/.test(this.secondRecipeData[j].scale))) {
                        this.$message.error('小料单中材料分量请输入数字！')
                        return false
                    }
                }
            }
            return true
        },
        // 接口请求，配方信息添加
        saveRecipe() {
            if (this.searchFlag === true) {
                this.updateRecipe()
                return
            }
            if (!this.checkParam()) {
                return false
            }
            var params = this.paramInit()
            this.$http.post(this, '/recipe/add', params, res => {
                if (res.data.errCode === this.$http.SUCCESS) {
                    this.resetForm()
                    this.$message({
                        message: '配方添加成功！',
                        type: 'success'
                    })
                    this.getRecipeList()
                } else if (res.data.errCode === this.$http.INSERT_FAIL_1) {
                    this.$confirm('配方信息已存在，确认是否修改', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.updateRecipe(params)
                    }).catch(() => {
                    })
                } else {
                    this.$message.error('配方信息添加失败')
                }
            })
        },
        // 接口请求，配方信息修改
        updateRecipe(params) {
            if (!this.checkParam()) {
                return false
            }
            this.$prompt('请输入密码，确认修改', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputPattern: /^(\w){6,25}$/,
                inputType: 'password',
                inputErrorMessage: '密码格式不正确'
            }).then(({ value }) => {
                if (!params) {
                    params = this.paramInit()
                }
                params.verifyInfo = {
                    password: value
                }
                this.$http.post(this, '/recipe/update', params, res2 => {
                    if (res2.data.errCode === this.$http.SUCCESS) {
                        this.resetForm()
                        this.$message({
                            type: 'success',
                            message: '配方信息修改成功!'
                        })
                    } else if (res2.data.errCode === this.$http.UPDATE_FAIL_1) {
                        this.$message.error('密码错误！')
                    } else {
                        this.$message.error('配方信息修改失败')
                    }
                })
            }).catch(() => {})
        },
        // 接口请求，删除配方信息
        deleteRecipe() {
            this.$prompt('配方信息删除后无法恢复，请输入密码，确认删除', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputPattern: /^(\w){6,25}$/,
                inputType: 'password',
                inputErrorMessage: '密码格式不正确'
            }).then(({value}) => {
                this.$http.post(this, '/recipe/delete', {recipeId: this.recipeId, password: value}, res => {
                    if (res.data.errCode === this.$http.SUCCESS) {
                        this.resetForm()
                        this.$message({
                            type: 'success',
                            message: '配方信息删除成功!'
                        })
                    } else if (res.data.errCode === this.$http.DELETE_FAIL_1) {
                        this.$message.error('密码错误！')
                    } else {
                        this.$message.error('配方信息删除失败')
                    }
                })
            }).catch(() => {})
        }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.select-width{
    width: 280px;
}
.select-width-max{
    width: 330px;
}
</style>
