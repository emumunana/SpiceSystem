<template>
    <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="0">原材料信息维护</el-menu-item>
          <el-menu-item index="1">配方信息维护</el-menu-item>
          <el-menu-item index="2">生产单生成</el-menu-item>
          <el-menu-item index="3">密码修改</el-menu-item>
        </el-menu>
      <router-view/>
    </div>
</template>
<script>
export default {
    data() {
        return {
            activeIndex: '0',
            pathList: [
                '/',
                '/recipe',
                '/production',
                '/password'
            ]
        }
    },
    created() {
        var path = this.$route.fullPath
        if (path === '/') {
            this.activeIndex = '0'
        } else if (path === '/recipe') {
            this.activeIndex = '1'
        } else if (path === '/production') {
            this.activeIndex = '2'
        } else {
            this.activeIndex = '3'
        }
    },
    methods: {
        handleSelect(key, keyPath) {
            this.$router.push(this.pathList[key])
        }
    }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
