<template>
    <div>
        <div class="page-area">
            <div class="title"><i class="el-icon-menu"></i>密码修改</div>
            <div class="display-flex">
                <div class="form-label label-width">原密码</div>
                <el-input class="input-width" type="password" v-model="oldPassword" placeholder="原密码"></el-input>
            </div>
            <div class="display-flex">
                <div class="form-label label-width" >新密码</div>
                <el-input class="input-width" type="password" v-model="newPassword" placeholder="新密码"></el-input>
            </div>
            <div class="display-flex">
                <div class="form-label label-width">再次输入新密码</div>
                <el-input class="input-width" type="password" v-model="newPassword2" placeholder="再次输入新密码"></el-input>
            </div>
            <div class="display-flex">
                <el-button class="submit-btn" @click="changePassword" type="primary" icon="el-icon-edit">修改</el-button>
                <el-button type="info" @click="resetForm" icon="el-icon-refresh">重置</el-button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            oldPassword: '',
            newPassword: '',
            newPassword2: ''
        }
    },
    methods: {
        // 重置表单
        resetForm() {
            this.oldPassword = ''
            this.newPassword = ''
            this.newPassword2 = ''
        },
        changePassword() {
            if (this.$util.isNull(this.oldPassword)) {
                this.$message.error('原密码不能为空！')
                return false
            }
            if (this.$util.isNull(this.newPassword)) {
                this.$message.error('新密码不能为空！')
                return false
            }
            if (!this.$util.verifyPassword(this.newPassword)) {
                this.$message.error('密码只能输入6-20个字母、数字、下划线')
                return false
            }
            if (this.$util.isNull(this.newPassword2)) {
                this.$message.error('请再次输入新密码！')
                return false
            }
            if (this.newPassword !== this.newPassword2) {
                this.$message.error('新密码两次输入不一致！')
                return false
            }
            var params = {
                oldPassword: this.oldPassword,
                newPassword: this.newPassword
            }
            this.$http.post(this, '/user/change', params, res => {
                if (res.data.errCode === this.$http.SUCCESS) {
                    this.oldPassword = ''
                    this.newPassword = ''
                    this.newPassword2 = ''
                    this.$message({
                        message: '密码修改成功！',
                        type: 'success'
                    })
                } else {
                    this.oldPassword = ''
                    this.$message.error('密码修改失败')
                }
            })
        }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.label-width{
    width: 180px;
}
.input-width{
    width: 300px;
}
</style>
