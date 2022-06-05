<template>
<div>
    <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-upload"></i>
        {{text}}
    </button>
    <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
</div>
</template>

<script>
    export default {
        name: 'file',
        props: {
            text: {
                default: "上传文件"
            },
            inputId: {
                default: "file-upload"
            },
            suffixs: {
                default: []
            },
            afterUpload: {
                type: Function,
                default: null
            },
        },
        data: function () {
            return {
            }
        },
        methods: {
            uploadFile(){
                let _this = this;
                let formData=new window.FormData();
                let file=_this.$refs.file.files[0];
                //文件后缀名判断
                let suffixs=_this.suffixs;
                let filename=file.name;
                let suffix=filename.substring(filename.lastIndexOf(".")+1,filename.length).toLowerCase();
                let vaildateSuffix=false;
                for (let i = 0; i <suffixs.length ; i++) {
                    if (suffix==suffixs[i].toLowerCase()){
                        vaildateSuffix=true;
                        break;
                    }
                }
                if (!vaildateSuffix){
                    Toast.warning("文件格式不正确，只支持上传："+suffixs.join(","));
                    $("#" + _this.inputId + "-input").val("");
                    return;
                }
                formData.append('file',file);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload',formData).then((response) => {
                    let resp=response.data;
                    console.log("文件上传成功地址：",resp);
                    _this.afterUpload(resp);
                    $("#" + _this.inputId + "-input").val("");
                });
            },
            selectFile(){
                let _this = this;
                $("#" + _this.inputId + "-input").trigger("click");
            }
        }
    }
</script>

<style scoped>
    .pagination {
        vertical-align: middle !important;
        font-size: 16px;
        margin-top: 0;
        margin-bottom: 10px;
    }

    .pagination button {
        margin-right: 5px;
    }

    .btn-primary.active {
        background-color: #2f7bba !important;
        border-color: #27689d !important;
        color: white !important;
        font-weight: 600;
    }

    /*.pagination select {*/
    /*vertical-align: middle !important;*/
    /*font-size: 16px;*/
    /*margin-top: 0;*/
    /*}*/
</style>
