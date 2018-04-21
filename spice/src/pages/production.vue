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
                </div>
            </div>
            <div class="title"><i class="el-icon-menu"></i>生产单信息</div>
            <div class="title"><i class="el-icon-menu"></i>大料单</div>
            <el-card class="box-card">
            <table :class="mainPrintHead">
                <tr>
                    <td><label style="font-size:20px;">{{recipeId}}  {{recipeName}}</label></td>
                </tr>
                <tr>
                    <td class="head-td">日期：{{nowDate}}</td>
                    <td class="head-td">
                        <label>批号：</label>
                        <label class="print-item">{{proNo}}</label>
                        <el-input v-model="proNo"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>标签：</label>
                        <label class="print-item">{{tagVal}}，</label>
                        <label class="print-item">{{tagVal2}}</label>
                        <el-select class="select-width" v-model="tagVal" filterable placeholder="请选择">
                            <el-option v-for="(item, index) in tagList" :key="index" :label="item" :value="item"></el-option>
                        </el-select>
                        <el-select class="select-width" v-model="tagVal2" filterable placeholder="请选择">
                            <el-option v-for="(item, index) in tagList2" :key="index" :label="item" :value="item"></el-option>
                        </el-select>
                    </td>
                    <td>
                        <label>品名：</label>
                        <label class="print-item">{{proName}}</label>
                        <el-input v-model="proName"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>发货：</label>
                        <label class="print-item">{{proSaleSize}}</label>
                        <el-input v-model="proSaleSize"></el-input>
                        <label>KG</label>
                    </td>
                    <td>
                        <label>产量：</label>
                        <label class="print-item">{{proSize}}</label>
                        <el-input v-model="proSize" placeholder=""></el-input>
                        <label>KG</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>包装：</label>
                        <label class="print-item">{{packageSize}}</label>
                        <el-select class="select-width" v-model="packageSize" filterable placeholder="请选择">
                            <el-option v-for="(item, index) in packageSizeList" :key="index" :label="item" :value="item"></el-option>
                        </el-select>
                    </td>
                    <td>
                        <label>备注：</label>
                        <label class="print-item">{{remark}}</label>
                        <el-input v-model="remark"></el-input>
                    </td>
                </tr>
            </table>
            <div :class="productionClass" style="margin-top:20px;">
                <table cellspacing="0" style="width: 860px;">
                    <tr>
                        <td style="width: 60px;">序号</td>
                        <td style="width: 160px;">编码</td>
                        <td style="width: 320px;">名称</td>
                        <td style="width: 110px;">添加量 / 克</td>
                        <td style="width: 110px;">确认</td>
                        <td style="width: 80px;">备注</td>
                    </tr>
                    <tr v-for="(item, index) in mainRecipeData" v-bind:key="item.materialInfo.materialId">
                        <td>{{index + 1}}</td>
                        <td style="text-align:left;padding-left:5px;">{{item.materialInfo.materialId}}</td>
                        <td v-if="index ===  (mainRecipeData.length -1)" style="text-align:left;padding-left:5px;">{{proNo}} {{item.materialInfo.materialName}}</td>
                        <td v-else style="text-align:left;padding-left:5px;">{{item.materialInfo.materialName}}</td>
                        <td style="text-align:right;padding-right:5px;">{{countScale(item.scale)}}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>合计</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:right;padding-right:5px;">{{$util.formatNum((Math.round(mainRecipeScale * parseFloat(proSize ? proSize : 0) * 100000)) / 10000)}}</td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <div class="display-flex table-footer">
                    <div style="width:450px;">操作人：</div>
                    <!-- <div style="text-align:center;width:180px;">{{(Math.round(mainRecipeScale * parseFloat(proSize ? proSize : 0) * 100)) / 10}}</div> -->
                    <div style="width:150px;margin-left:30px;">复核：</div>
                </div>
                <div class="display-flex table-footer">
                    <div style="width:450px;">日期：</div>
                </div>
            </div>
            </el-card>
            <div>
                <el-button style="margin-top: 10px;" @click="printProduction" type="primary" icon="el-icon-printer">打印</el-button>
                <!-- <label>总量：{{(Math.round(mainRecipeScale * parseFloat(proSize ? proSize : 0) * 100)) / 10}}</label> -->
            </div>
            <div class="title"><i class="el-icon-menu"></i>小料单</div>
            <el-card class="box-card">
            <table :class="secondPrintHead">
                <tr>
                    <td>
                        <label style="font-size:20px;">{{proNo}} 小料单</label>
                    </td>
                </tr>
                <tr>
                    <td>日期：{{nowDate}}</td>
                </tr>
            </table>
            <div :class="productionClass2" style="margin-top:20px;">
                <table cellspacing="0" style="width: 860px;">
                    <tr>
                        <td style="width: 60px;">序号</td>
                        <td style="width: 160px;">编码</td>
                        <td style="width: 320px;">名称</td>
                        <td style="width: 110px;">添加量 / 克</td>
                        <td style="width: 110px;">确认</td>
                        <td style="width: 80px;">备注</td>
                    </tr>
                    <tr v-for="(item, index) in secondRecipeData" v-bind:key="item.materialInfo.materialId">
                        <td>{{index + 1}}</td>
                        <td style="text-align:left;padding-left:5px;">{{item.materialInfo.materialId}}</td>
                        <td style="text-align:left;padding-left:5px;">{{item.materialInfo.materialName}}</td>
                        <td style="text-align:right;padding-right:5px;">{{countScale(item.scale)}}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>合计</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:right;padding-right:5px;">{{$util.formatNum((Math.round(secondRecipeScale * parseFloat(proSize ? proSize : 0) * 100000)) / 10000)}}</td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <div class="display-flex table-footer">
                    <div style="width:450px;">操作人：</div>
                    <!-- <div style="text-align:center;width:180px;">{{(Math.round(secondRecipeScale * parseFloat(proSize ? proSize : 0) * 100)) / 10}}</div> -->
                    <div style="width:150px;margin-left:30px;">复核：</div>
                </div>
                <div class="display-flex table-footer">
                    <div style="width:450px;">日期：</div>
                </div>
            </div>
            </el-card>
            <div>
                <el-button style="margin-top: 10px;" @click="printProduction2" type="primary" icon="el-icon-printer">打印</el-button>
                <!-- <label>总量：{{(Math.round(secondRecipeScale * parseFloat(proSize ? proSize : 0) * 100)) / 10}}</label> -->
            </div>
            <div :class="productionClass3" style="margin-top:20px;">
                <table cellspacing="0" style="width: 860px;">
                    <tr>
                        <td style="width: 60px;">序号</td>
                        <td style="width: 160px;">编码</td>
                        <td style="width: 320px;">名称</td>
                        <td style="width: 110px;">添加量 / 克</td>
                        <td style="width: 110px;">确认</td>
                        <td style="width: 80px;">备注</td>
                    </tr>
                    <tr v-for="(item, index) in mainRecipeData" v-if="index !==  (mainRecipeData.length -1)" v-bind:key="item.materialInfo.materialId">
                        <td>{{index + 1}}</td>
                        <td style="text-align:left;padding-left:5px;">{{item.materialInfo.materialId}}</td>
                        <td style="text-align:left;padding-left:5px;">{{item.materialInfo.materialName}}</td>
                        <td style="text-align:right;padding-right:5px;">{{countScale(item.scale)}}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr v-for="(item, index) in secondRecipeData" v-bind:key="item.materialInfo.materialId">
                        <td>{{index + mainRecipeData.length}}</td>
                        <td style="text-align:left;padding-left:5px;">{{item.materialInfo.materialId}}</td>
                        <td style="text-align:left;padding-left:5px;">{{item.materialInfo.materialName}}</td>
                        <td style="text-align:right;padding-right:5px;">{{countScale(item.scale)}}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>合计</td>
                        <td></td>
                        <td></td>
                        <td style="text-align:right;padding-right:5px;">{{$util.formatNum((Math.round(mainRecipeScale * parseFloat(proSize ? proSize : 0) * 100000)) / 10000)}}</td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <div class="display-flex table-footer">
                    <div style="width:450px;">操作人：</div>
                    <!-- <div style="text-align:center;width:180px;">{{(Math.round(mainRecipeScale * parseFloat(proSize ? proSize : 0) * 100)) / 10}}</div> -->
                    <div style="width:150px;margin-left:30px;">复核：</div>
                </div>
                <div class="display-flex table-footer">
                    <div style="width:450px;">日期：</div>
                </div>
            </div>
            <div>
                <el-button style="margin-top: 10px;" @click="printAll" type="primary" icon="el-icon-printer">合并打印</el-button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            searchKey: '', // 搜索关键字
            nowDate: '', // 当前时间
            recipeList: [], // 配方列表
            materialList: [], // 原材料列表
            recipeId: '', // 新增修改的配方ID
            recipeName: '', // 新增修改的配方名称
            mainRecipeData: [
                // {
                //     materialInfo: null, // 材料详情
                //     scale: '' // 分量
                // }
            ], // 大料单信息
            secondRecipeData: [
                // {
                //     materialInfo: null, // 材料详情
                //     scale: '' // 分量
                // }
            ], // 小料单信息
            tagVal: '', // 标签值1
            tagVal2: '', // 标签值2
            proNo: '', // 批号
            proName: '', // 产品名称
            proSaleSize: '0', // 发货数量
            proSize: '0', // 生产数量
            packageSize: '', // 包装大小
            remark: '', // 备注
            tagList: [
                '银沙中文标签',
                '联福中文标签',
                '联福英文标签',
                '中性中文标签',
                '中性英文标签',
                '其它'
            ], // 标签信息枚举
            tagList2: [
                '洗衣液', '皂液', '柔顺剂', '洗衣片', '洗衣粉', '洁厕剂', '清洗剂', '地板水', '蜡烛', '素油', 'PVC', '洗发水', '沐浴露', '洗面奶', '洗手液', '空清', '香膏', '湿纸巾', '扩香机精油', '香水', '护肤品', '香体露', '佛像', '车蜡，标板蜡', '除锈剂', '手工皂', '机制皂', '冷制皂', '其它'
            ], // 标签信息枚举
            packageSizeList: [
                '5L 塑料桶',
                '25L 塑料桶',
                '25L 铁桶',
                '其它'
            ], // 包装信息枚举
            mainRecipeScale: 0, // 大料单单位总量统计
            secondRecipeScale: 0, // 小料单单位总量统计
            productionClass: 'production-info', // 控制打印时显示的大料单信息
            productionClass2: 'production-info', // 控制打印时显示的小料单信息
            productionClass3: 'production-info display-none', // 控制打印时显示的合并材料信息
            mainPrintHead: '', // 控制打印时显示大料单头部信息
            secondPrintHead: '' // 控制打印时显示小料单头部信息
        }
    },
    created() {
        var date = new Date()
        this.nowDate = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
        this.getRecipeList()
        this.getMaterialList()
    },
    methods: {
        // 计算每种材料分量
        countScale(scale) {
            if (this.proSize) {
                return this.$util.formatNum((Math.round(parseFloat(scale) * parseFloat(this.proSize) * 100000)) / 10000)
            } else {
                return 0
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
                    var mainRecipeDataTemp = []
                    var mainRecipeScale = 0
                    for (var i = 0; i < result.mainRecipe.length; i++) {
                        var matchFlag = false
                        for (var j = 0; j < this.materialList.length; j++) {
                            if (this.materialList[j].materialId === result.mainRecipe[i].materialInfo.materialId) {
                                matchFlag = true
                                mainRecipeDataTemp.push({
                                    materialInfo: this.materialList[j],
                                    scale: result.mainRecipe[i].scale
                                })
                                mainRecipeScale = mainRecipeScale + parseFloat(result.mainRecipe[i].scale)
                                break
                            }
                        }
                        if (!matchFlag) {
                            mainRecipeDataTemp.push({
                                materialInfo: result.mainRecipe[i].materialInfo,
                                scale: result.mainRecipe[i].scale
                            })
                            mainRecipeScale = mainRecipeScale + parseFloat(result.mainRecipe[i].scale)
                        }
                    }
                    this.mainRecipeData = mainRecipeDataTemp
                    var secondRecipeDataTemp = []
                    var secondRecipeScale = 0
                    for (var n = 0; n < result.secondRecipe.length; n++) {
                        var matchFlag2 = false
                        for (var m = 0; m < this.materialList.length; m++) {
                            if (this.materialList[m].materialId === result.secondRecipe[n].materialInfo.materialId) {
                                matchFlag2 = true
                                secondRecipeDataTemp.push({
                                    materialInfo: this.materialList[m],
                                    scale: result.secondRecipe[n].scale
                                })
                                secondRecipeScale = secondRecipeScale + parseFloat(result.secondRecipe[n].scale)
                                break
                            }
                        }
                        if (!matchFlag2) {
                            secondRecipeDataTemp.push({
                                materialInfo: result.secondRecipe[n].materialInfo,
                                scale: result.secondRecipe[n].scale
                            })
                            secondRecipeScale = secondRecipeScale + parseFloat(result.secondRecipe[n].scale)
                        }
                    }
                    mainRecipeDataTemp.push({
                        materialInfo: {
                            materialId: '',
                            materialName: '小料'
                        },
                        scale: secondRecipeScale
                    })
                    this.mainRecipeScale = mainRecipeScale + secondRecipeScale
                    this.secondRecipeScale = secondRecipeScale
                    this.secondRecipeData = secondRecipeDataTemp
                } else {
                    this.$message.error('配方信息查询失败！')
                }
            })
        },
        // 生产单输入项校验
        checkInput() {
            if (this.proNo === '' || this.proNo === null || this.proNo === undefined) {
                this.$message.error('批号不能为空！')
                return false
            }
            if (this.tagVal === '' || this.tagVal === null || this.tagVal === undefined) {
                this.$message.error('标签不能为空！')
                return false
            }
            if (this.tagVal2 === '' || this.tagVal2 === null || this.tagVal2 === undefined) {
                this.$message.error('标签不能为空！')
                return false
            }
            if (this.proName === '' || this.proName === null || this.proName === undefined) {
                this.$message.error('品名不能为空！')
                return false
            }
            if (this.proSaleSize === '' || this.proSaleSize === null || this.proSaleSize === undefined) {
                this.$message.error('发货数量不能为空！')
                return false
            }
            if (!(/^(-?\d+)(\.\d+)?$/.test(this.proSaleSize))) {
                this.$message.error('发货数量只能输入数字！')
                return false
            }
            if (parseFloat(this.proSaleSize) < 0) {
                this.$message.error('发货数量不能小于0！')
                return false
            }
            if (this.proSize === '' || this.proSize === null || this.proSize === undefined) {
                this.$message.error('生产数量不能为空！')
                return false
            }
            if (!(/^(-?\d+)(\.\d+)?$/.test(this.proSize))) {
                this.$message.error('生产数量只能输入数字！')
                return false
            }
            if (parseFloat(this.proSize) <= 0) {
                this.$message.error('生产数量必须大于0！')
                return false
            }
            if (this.packageSize === '' || this.packageSize === null || this.packageSize === undefined) {
                this.$message.error('包装大小不能为空！')
                return false
            }
            return true
        },
        // 大料单打印
        printProduction() {
            if (!this.checkInput()) {
                return
            }
            this.productionClass = 'production-info print-object'
            this.productionClass2 = 'production-info print-display-none'
            this.productionClass3 = 'production-info display-none'
            this.mainPrintHead = 'print-head'
            this.secondPrintHead = ''
            setTimeout(function() {
                window.print()
            }, 500)
        },
        // 小料单打印
        printProduction2() {
            if (!this.checkInput()) {
                return
            }
            this.productionClass2 = 'production-info print-object2'
            this.productionClass = 'production-info print-display-none'
            this.productionClass3 = 'production-info display-none'
            this.mainPrintHead = ''
            this.secondPrintHead = 'print-head'
            setTimeout(function() {
                window.print()
            }, 500)
        },
        printAll() {
            if (!this.checkInput()) {
                return
            }
            this.productionClass3 = 'production-info print-all print-object display-none'
            this.productionClass = 'production-info print-display-none'
            this.productionClass2 = 'production-info print-display-none'
            this.mainPrintHead = 'print-head'
            this.secondPrintHead = ''
            setTimeout(function() {
                window.print()
            }, 500)
        }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.production-info table tr td{
    border: 1px solid #000;
    text-align: center;
    word-break:break-all;
}
.table-footer {
    display: none;
}
.el-input{
    width: 220px;
}
.print-item{
    display: none;
}
.display-none {
    display: none;
}
@media print{
    *{
        visibility: hidden
    }
    table{
        font-size: 18px;
    }
    .print-head{
        position: absolute;
        top: 0;
        left: 50px;
        visibility: visible;
    }
    .print-head .el-select{
        display: none;
    }
    .print-head .el-input{
        display: none;
    }
    .print-item{
        display: inline;
    }
    .table-footer {
        display: flex;
    }
    .print-object{
        position: absolute;
        top: 140px;
        left: 50px;
        visibility: visible;
    }
    .print-object2{
        position: absolute;
        top: 60px;
        left: 50px;
        visibility: visible;
    }
    .print-object *,.print-head *{
        visibility: visible;
    }
    .print-object2 *,.print-head *{
        visibility: visible;
    }
    .head-td{
        width: 450px;
    }
    .print-all{
        display: block;
    }
    .print-display-none{
        display: none;
    }
}
</style>
