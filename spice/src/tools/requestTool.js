// axios
import Axios from 'axios'
// import Qs from 'qs'

const URL_PERFIX = '/spice'

export default {
    SUCCESS: '000000',
    SELECT_FAIL: '000001',
    INSERT_FAIL: '000002',
    INSERT_FAIL_1: '000002-1',
    UPDATE_FAIL: '000003',
    DELETE_FAIL: '000004',
    UPDATE_FAIL_1: '000003-1',
    DELETE_FAIL_1: '000004-1',
    /**
    * 获取Axios对象
    */
    obj() {
        return Axios
    },
    /**
     * GET请求
     * @param {Object} _this this对象
     * @param {String} url 请求地址
     * @param {Object} params 请求参数
     * @param {Function} callback 成功回调
     */
    get(_this, url, params, callback) {
        // let loadingTarget = Loading.service({ fullscreen: true })
        _this.$util.show_loading()
        let paramsStr = ''
        for (let i in params) {
            paramsStr += '&' + i + '=' + params[i]
        }
        paramsStr = paramsStr.substring(1)
        url = url + '?' + paramsStr
        return Axios.get(URL_PERFIX + url).then(function(res) {
            _this.$util.hide_loading()
            callback(res)
        }).catch(function(err) {
            _this.$util.hide_loading()
            _this.$message.error('[' + err.code + ']' + ' ' + err.message)
        })
    },
    /**
     * POST请求
     * @param {Object} _this this对象
     * @param {String} url 请求地址
     * @param {Object} params 请求参数
     * @param {Function} callback 成功回调
     */
    post(_this, url, params, callback) {
        // let loadingTarget = Loading.service({ fullscreen: true })
        _this.$util.show_loading()
        Axios.defaults.headers.post['Content-Type'] = 'application/json'
        return Axios.post(URL_PERFIX + url, params).then(function(res) {
            _this.$util.hide_loading()
            callback(res)
        }).catch(function(err) {
            _this.$util.hide_loading()
            _this.$message.error(err.message)
        })
    }
}
