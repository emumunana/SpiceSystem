<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<script>
import {Loading} from 'element-ui'
export default {
    name: 'app',
    create() {
        let loadingTarget = Loading.service({ fullscreen: true })
        setTimeout(() => {
            loadingTarget.close()
        }, 1000)
    }
}
</script>

<style>
body{
    margin: 0;
    background-color: #ededed;
}
#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    /* text-align: center; */
    color: #2c3e50;
}
.title{
    /* border-left:5px solid rgb(255, 208, 75); */
    height: 20px;
    line-height: 20px;
    text-align: left;
    margin-bottom: 10px;
    padding: 10px;
}
.display-flex{
    display: flex;
    margin: 10px 0;
}
.form-label{
    width: 100px;
    height: 40px;
    line-height: 40px;
    margin: 0 10px;
}
.page-area{
    border: 1px solid #b2b2b2;
    /* border-radius: 10px; */
    padding: 10px;
    margin: 10px;
    background-color: #fff;
}
.submit-btn{
    margin: 0 20px;
}
.table-area{
    margin: 10px 0;
}
.input-width{
    width: 230px;
}
</style>
