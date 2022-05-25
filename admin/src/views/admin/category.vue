<template>
    <div>
        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>
            &nbsp;
            <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                                        <th>id</th>
                        <th>父id</th>
                        <th>名称</th>
                        <th>顺序</th>
            <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="category in categorys">
                            <td>{{category.id}}</td>
                            <td>{{category.parent}}</td>
                            <td>{{category.name}}</td>
                            <td>{{category.sort}}</td>
        <td>
            <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
            </div>
        </td>
        </tr>
        </tbody>
        </table>

        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">父id</label>
                                            <div class="col-sm-10">
                                                <input v-model="category.parent" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">名称</label>
                                            <div class="col-sm-10">
                                                <input v-model="category.name" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">顺序</label>
                                            <div class="col-sm-10">
                                                <input v-model="category.sort" class="form-control">
                                            </div>
                                        </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    export default {
        components: {Pagination},
        name: "business-category",
        data :function(){
            return {
                category: {},
                categorys : [],
            }
        },
        mounted: function () {
            // this.$parent.activeSidebar("business-category-sidebar");
            let _this=this;
            _this.$refs.pagination.size=5;
            _this.list(1);
        } ,
        methods:{
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.category = {};
                $("#form-modal").modal("show");
            },
            edit(category) {
                let _this = this;
                _this.category = $.extend({},category);
                $("#form-modal").modal("show");
            },
            list(page){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/list',{
                    page:page,
                    size:_this.$refs.pagination.size,
                }).then((response) => {
                    console.log("查询分类列表结果：", response);
                    let resp=response.data;
                    _this.categorys=resp.content.list;
                    _this.$refs.pagination.render(page,resp.content.total);
                })
            },
            save(page){
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.category.parent, "父id")
                    || !Validator.require(_this.category.name, "名称")
                    || !Validator.length(_this.category.name, "名称", 1, 50)
                ) {
                    return;
                }
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save',
                    _this.category).then((response) => {
                    console.log("保存分类列表结果：", response);
                    let resp=response.data;
                    if (resp.success){
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功")
                    }else {
                        Toast.warning(resp.message)
                    }
                })
            },
            del(id){
                let _this = this;
                Confirm.show("删除分类后不可恢复，是否删除？",function () {
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id).then((response) => {
                        console.log("删除分类列表结果：", response);
                        let resp=response.data;
                        if (resp.success){
                            _this.list(1);
                            Toast.success("删除成功!")
                        }
                    })
                });
            }
        }
    }
</script>

