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
        if ((/^(-?\d+)(\.\d+)?$/.test(val))) {
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
    },
    formatNum(num) {
        console.log(num)
        var numStr = num.toString()
        if (this.isNum(numStr)) {
            var pointIndex = numStr.indexOf('.')
            var pointNum = '00'
            var mainNum = numStr
            var numFlag = ''
            if (pointIndex !== -1) {
                pointNum = numStr.substring(pointIndex + 1, numStr.length)
                if (pointNum.length === 1) {
                    pointNum = pointNum + '0'
                }
                mainNum = numStr.substring(0, pointIndex)
            }
            if (numStr.indexOf('-') !== -1) {
                numFlag = '-'
                mainNum = mainNum.substring(1, mainNum.length)
            }
            var i = 0
            var formatNum = ''
            while (mainNum.length - i > 0) {
                var startIndex = mainNum.length - i - 3
                var sub = mainNum.substring(startIndex >= 0 ? startIndex : 0, mainNum.length - i)
                formatNum = (formatNum === '' ? sub : sub + ',' + formatNum)
                i += 3
            }
            console.log(numFlag + formatNum + '.' + pointNum)
            return numFlag + formatNum + '.' + pointNum
        } else {
            return num
        }
    }
}
