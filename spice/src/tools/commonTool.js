import {Loading} from 'element-ui'
export default {
    _LOADING: null,
    show_loading() {
        this._LOADING = Loading.service({ fullscreen: true })
    },

    hide_loading() {
        if (this._LOADING) {
            setTimeout(() => {
                this._LOADING.close()
            }, 1000)
        }
    },
    isNull(val) {
        if (val === '' || val === null || val === undefined) {
            return true
        } else {
            return false
        }
    },
    isNum(val) {
        if (!(/^(-?\d+)(\.\d+)?$/.test(val))) {
            return true
        } else {
            return false
        }
    },
    verifyPassword(pass) {
        if (/^(\w){6,20}$/.test(pass)) {
            return true
        } else {
            return false
        }
    }
}
