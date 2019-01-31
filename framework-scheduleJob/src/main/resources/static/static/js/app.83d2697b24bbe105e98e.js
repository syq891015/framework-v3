webpackJsonp([1],{Ad7c:function(e,t){},NHnr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l=a("7+uW"),o=a("woOf"),s=a.n(o),n=a("//Fk"),i=a.n(n),r=a("mtWM"),c=a.n(r),u=a("zL8q"),m=a.n(u),d=c.a.create({baseURL:"",timeout:6e4,withCredentials:!0,headers:{"X-Requested-with":"XMLHttpRequest"}});d.interceptors.request.use(function(e){return e},function(e){console.log(e),i.a.reject(e)}),d.interceptors.response.use(function(e){var t=e.data;return 0!==t.code?(Object(u.Message)({message:t.msg,type:"error",duration:1500}),i.a.reject(t)):t},function(e){return console.log(e),Object(u.Message)({message:e.message,type:"error",duration:1500}),i.a.reject(e)});var g=d;var h={name:"scheduleJobLog",props:["jobId"],data:function(){return{list:null,total:null,listLoading:!0,listQuery:{pageNum:1,pageSize:20,jobId:void 0}}},created:function(){this.listQuery.jobId=this.jobId,this.getList()},methods:{handleSizeChange:function(e){this.listQuery.pageSize=e,this.getList()},handleCurrentChange:function(e){this.listQuery.pageNum=e,this.getList()},handleFilter:function(){this.listQuery.pageNum=1,this.getList()},getList:function(){var e,t=this;this.listLoading=!0,(e=this.listQuery,g({url:"/sys/scheduleJobLogs",method:"get",params:e})).then(function(e){t.list=e.data.list,t.total=e.data.total,t.listLoading=!1})}}},p={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-col",{staticClass:"toolbar",staticStyle:{"padding-bottom":"0"},attrs:{span:24}},[a("el-form",{attrs:{inline:!0,model:e.listQuery,"label-position":"left"}},[a("el-form-item",{attrs:{label:e.$t("scheduleJobLog.jobId")}},[a("el-input",{attrs:{placeholder:e.$t("scheduleJobLog.jobId"),clearable:""},on:{clear:function(t){e.listQuery.jobId=void 0}},model:{value:e.listQuery.jobId,callback:function(t){e.$set(e.listQuery,"jobId",t)},expression:"listQuery.jobId"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("common.search")))]),e._v(" "),a("el-button",{attrs:{type:"danger"},on:{click:function(t){e.$emit("back")}}},[e._v(e._s(e.$t("common.back")))])],1)],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],ref:"dataTable",attrs:{data:e.list,"element-loading-text":e.$t("common.loadingText"),border:"",fit:!0,"highlight-current-row":"",stripe:"","tooltip-effect":"light"}},[a("el-table-column",{attrs:{type:"index",width:"50",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.logId"),width:"70",prop:"logId",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.jobId"),width:"70",prop:"jobId",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.beanName"),prop:"beanName",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.methodName"),prop:"methodName",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.params"),prop:"params",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.status"),align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.status?a("el-tag",{attrs:{type:"success"}},[e._v(e._s(e.$t("common.success")))]):e._e(),e._v(" "),0===t.row.status?a("el-tag",{attrs:{type:"danger"}},[e._v(e._s(e.$t("common.failure")))]):e._e()]}}])}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.error"),prop:"error",align:"center","show-overflow-tooltip":""}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.times"),prop:"times",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJobLog.createTime"),prop:"createTime",align:"center"}})],1),e._v(" "),a("el-pagination",{attrs:{background:"","current-page":e.listQuery.pageNum,"page-sizes":[20,30,50,100],"page-size":e.listQuery.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)},staticRenderFns:[]},b={name:"scheduleJob",components:{scheduleJobLog:a("VU/8")(h,p,!1,null,null,null).exports},data:function(){return{tableKey:0,list:null,total:null,listLoading:!0,listQuery:{pageNum:1,pageSize:20,beanName:void 0},multipleSelection:[],temp:{jobId:void 0,beanName:void 0,methodName:void 0,params:void 0,cronExpression:void 0,remark:void 0},dialogFormVisible:!1,dialogStatus:"",dialogTitle:"",rules:{beanName:[{required:!0,message:"bean名称不能为空",trigger:"change"},{max:200,message:"长度应小于200个字符",trigger:"change"}],methodName:[{required:!0,message:"方法名称不能为空",trigger:"change"},{max:100,message:"长度应小于100个字符",trigger:"change"}],cronExpression:[{required:!0,message:"cron表达式不能为空",trigger:"change"},{max:100,message:"长度应小于100个字符",trigger:"change"}],params:[{max:2e3,message:"长度应小于2000个字符",trigger:"change"}],remark:[{max:255,message:"长度应小于255个字符",trigger:"change"}]},showLogFlag:!1,jobId:void 0}},created:function(){this.getList()},methods:{handleSizeChange:function(e){this.listQuery.pageSize=e,this.getList()},handleCurrentChange:function(e){this.listQuery.pageNum=e,this.getList()},handleRowClick:function(e){this.$refs.dataTable.toggleRowSelection(e)},handleSelectionChange:function(e){this.multipleSelection=e.reduce(function(e,t,a){return e.push(t),e},[])},handleFilter:function(){this.listQuery.pageNum=1,this.getList()},getList:function(){var e,t=this;this.listLoading=!0,(e=this.listQuery,g({url:"/sys/scheduleJobs",method:"get",params:e})).then(function(e){t.list=e.data.list,t.total=e.data.total,t.listLoading=!1})},resetTemp:function(){this.temp={jobId:void 0,beanName:void 0,methodName:void 0,params:void 0,cronExpression:void 0,remark:void 0}},handleCreate:function(){var e=this;this.resetTemp(),this.dialogStatus="add",this.dialogTitle=this.$t("common."+this.dialogStatus),this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},createData:function(){var e=this;this.$refs.dataForm.validate(function(t){var a;t&&(a=e.temp,g({url:"/sys/scheduleJobs",method:"post",data:a})).then(function(){e.dialogFormVisible=!1,e.$message.success({message:"保存成功!",onClose:function(){e.getList()}})})})},handleUpdate:function(){var e=this;0!==this.multipleSelection.length?this.multipleSelection.length>1?this.$message.warning({message:"只能选择一项任务！"}):(this.temp=s()({},this.multipleSelection[0]),this.dialogStatus="edit",this.dialogTitle=this.$t("common."+this.dialogStatus),this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})):this.$message.warning({message:"请选择一项任务！"})},updateData:function(){var e=this;this.$refs.dataForm.validate(function(t){if(t){var a=s()({},e.temp);(l=a,g({url:"/sys/scheduleJobs/"+l.jobId,method:"put",data:l})).then(function(){e.dialogFormVisible=!1,e.$message.success({message:"修改成功！",onClose:function(){e.getList()}})})}var l})},del:function(){var e,t=this;0!==this.multipleSelection.length?this.multipleSelection.length>1?this.$message.warning({message:"只能选择一项任务！"}):(e=this.multipleSelection[0].jobId,g({url:"/sys/scheduleJobs/"+e,method:"delete"})).then(function(e){0===e.code&&t.$message.success({message:"删除成功！",onClose:function(){t.getList()}})}):this.$message.warning({message:"请选择一项任务！"})},run:function(){var e=this;0!==this.multipleSelection.length?function(e){return g({url:"/sys/scheduleJobs/run",method:"post",data:e})}(this.multipleSelection.map(function(e){return e.jobId})).then(function(t){0===t.code&&e.$message.success({message:"执行成功！"})}):this.$message.warning({message:"至少选择一项任务！"})},pause:function(){var e=this;0!==this.multipleSelection.length?function(e){return g({url:"/sys/scheduleJobs/pause",method:"post",data:e})}(this.multipleSelection.map(function(e){return e.jobId})).then(function(t){0===t.code&&e.$message.success({message:"暂停成功！",onClose:function(){e.getList()}})}):this.$message.warning({message:"至少选择一项任务！"})},resume:function(){var e=this;0!==this.multipleSelection.length?function(e){return g({url:"/sys/scheduleJobs/resume",method:"post",data:e})}(this.multipleSelection.map(function(e){return e.jobId})).then(function(t){0===t.code&&e.$message.success({message:"恢复成功！",onClose:function(){e.getList()}})}):this.$message.warning({message:"至少选择一项任务！"})},showLog:function(){1===this.multipleSelection.length&&(this.jobId=this.multipleSelection[0].jobId),this.showLogFlag=!0},hideLog:function(){this.multipleSelection=[],this.jobId=void 0,this.showLogFlag=!1}}},f={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[e.showLogFlag?e._e():a("div",[a("el-col",{staticClass:"toolbar",staticStyle:{"padding-bottom":"0"},attrs:{span:24}},[a("el-form",{attrs:{inline:!0,model:e.listQuery,"label-position":"left"}},[a("el-form-item",{attrs:{label:e.$t("scheduleJob.beanName")}},[a("el-input",{attrs:{autofocus:"",placeholder:e.$t("scheduleJob.beanName"),clearable:""},model:{value:e.listQuery.beanName,callback:function(t){e.$set(e.listQuery,"beanName",t)},expression:"listQuery.beanName"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.$t("common.search")))]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-plus"},on:{click:e.handleCreate}},[e._v(e._s(e.$t("common.add")))]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-edit"},on:{click:e.handleUpdate}},[e._v(e._s(e.$t("common.edit")))]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-delete"},on:{click:e.del}},[e._v(e._s(e.$t("common.delete")))]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-fa fa-pause"},on:{click:e.pause}},[e._v(e._s(e.$t("common.pause")))]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-fa fa-reply"},on:{click:e.resume}},[e._v(e._s(e.$t("common.resume")))]),e._v(" "),a("el-button",{attrs:{icon:"el-icon-caret-right"},on:{click:e.run}},[e._v(e._s(e.$t("common.run")))]),e._v(" "),a("el-button",{attrs:{type:"danger"},on:{click:e.showLog}},[e._v(e._s(e.$t("common.log")))])],1)],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],ref:"dataTable",attrs:{data:e.list,"element-loading-text":e.$t("common.loadingText"),border:"",fit:!0,"highlight-current-row":"",stripe:"","tooltip-effect":"light"},on:{"selection-change":e.handleSelectionChange,"row-click":e.handleRowClick}},[a("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{type:"index",width:"50",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.jobId"),width:"70",prop:"jobId",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.beanName"),prop:"beanName",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.methodName"),prop:"methodName",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.params"),prop:"params",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.cronExpression"),prop:"cronExpression",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.status"),align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[0===t.row.status?a("el-tag",{attrs:{type:"success"}},[e._v(e._s(e.$t("common.normal")))]):e._e(),e._v(" "),1===t.row.status?a("el-tag",{attrs:{type:"danger"}},[e._v(e._s(e.$t("common.pause")))]):e._e()]}}])}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.remark"),prop:"remark",align:"center","show-overflow-tooltip":""}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("scheduleJob.createTime"),prop:"createTime",align:"center"}})],1),e._v(" "),a("el-pagination",{attrs:{background:"","current-page":e.listQuery.pageNum,"page-sizes":[20,30,50,100],"page-size":e.listQuery.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),a("el-dialog",{staticClass:"help-info",attrs:{"close-on-click-modal":!1,title:e.dialogTitle,visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"90%"},attrs:{rules:e.rules,model:e.temp,"label-position":"right","label-width":"120px"}},[a("el-form-item",{attrs:{label:e.$t("scheduleJob.beanName"),prop:"beanName"}},[a("el-input",{attrs:{clearable:""},model:{value:e.temp.beanName,callback:function(t){e.$set(e.temp,"beanName",t)},expression:"temp.beanName"}}),e._v(" "),a("el-alert",{attrs:{title:"Spring Bean名称；不能为空，长度应小于200个字符",closable:!1}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("scheduleJob.methodName"),prop:"methodName"}},[a("el-input",{attrs:{clearable:""},model:{value:e.temp.methodName,callback:function(t){e.$set(e.temp,"methodName",t)},expression:"temp.methodName"}}),e._v(" "),a("el-alert",{attrs:{title:"方法名称不能为空；长度应小于100个字符",closable:!1}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("scheduleJob.params"),prop:"params"}},[a("el-input",{attrs:{clearable:""},model:{value:e.temp.params,callback:function(t){e.$set(e.temp,"params",t)},expression:"temp.params"}}),e._v(" "),a("el-alert",{attrs:{title:"长度应小于2000个字符",closable:!1}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("scheduleJob.cronExpression"),prop:"cronExpression"}},[a("el-input",{attrs:{clearable:""},model:{value:e.temp.cronExpression,callback:function(t){e.$set(e.temp,"cronExpression",t)},expression:"temp.cronExpression"}}),e._v(" "),a("el-alert",{attrs:{title:"秒 分 时 日 月 星期 年（可选）；不能为空，长度应小于100个字符",closable:!1}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("scheduleJob.remark"),prop:"remark"}},[a("el-input",{attrs:{clearable:""},model:{value:e.temp.remark,callback:function(t){e.$set(e.temp,"remark",t)},expression:"temp.remark"}}),e._v(" "),a("el-alert",{attrs:{title:"长度应小于255个字符",closable:!1}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v(e._s(e.$t("common.cancel")))]),e._v(" "),"add"===e.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:e.createData}},[e._v(e._s(e.$t("common.confirm")))]):a("el-button",{attrs:{type:"primary"},on:{click:e.updateData}},[e._v(e._s(e.$t("common.confirm")))])],1)],1)],1),e._v(" "),e.showLogFlag?a("schedule-job-log",{attrs:{jobId:e.jobId},on:{back:e.hideLog}}):e._e()],1)},staticRenderFns:[]},v={name:"App",components:{ScheduleJob:a("VU/8")(b,f,!1,null,null,null).exports}},_={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("schedule-job")],1)},staticRenderFns:[]};var $=a("VU/8")(v,_,!1,function(e){a("kdKr")},null,null).exports,y=(a("tvR6"),a("Dd8w")),L=a.n(y),k=a("TXmL"),w=a("lbHh"),N=a.n(w),S=a("wUZ8"),x=a.n(S),J=a("Vi3T"),j=a.n(J);l.default.use(k.a);var I={en:L()({},{},x.a),zh:L()({},{common:{search:"搜索",add:"添加",edit:"修改",delete:"删除",back:"返回",cancel:"取 消",confirm:"确 定",export:"导出",actions:"操作",tableComment:"表备注",tableName:"表名",engine:"存储引擎",createTime:"创建时间",genCode:"生成代码",bound:"绑定",boundMenu:"权限分配/查看",boundRole:"绑定角色",boundOrg:"绑定机构",upload:"上传",download:"下载",pause:"暂停",resume:"恢复",run:"立即执行",normal:"正常",success:"成功",failure:"失败",log:"日志列表",loadingText:"加载中请稍后..."},scheduleJob:{jobId:"任务ID",beanName:"bean名称",methodName:"方法名称",params:"参数",cronExpression:"cron表达式",status:"状态",remark:"备注",createTime:"创建时间"},scheduleJobLog:{logId:"日志ID",jobId:"任务ID",beanName:"bean名称",methodName:"方法名称",params:"参数",status:"状态",error:"错误信息",times:"耗时(单位：毫秒)",createTime:"执行时间"}},j.a)},C=new k.a({locale:N.a.get("language")||"zh",messages:I});a("Ad7c"),a("e0XP");l.default.config.productionTip=!1,l.default.use(m.a,{size:"medium",i18n:function(e,t){return C.t(e,t)}}),new l.default({el:"#app",i18n:C,components:{App:$},template:"<App/>"})},e0XP:function(e,t){},kdKr:function(e,t){},tvR6:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.83d2697b24bbe105e98e.js.map