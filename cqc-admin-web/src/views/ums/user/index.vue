<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float:right"
          type="primary"
          @click="handleSearchList()"
          size="small">
          查询搜索
        </el-button>
        <el-button
          style="float:right;margin-right: 15px"
          @click="handleResetSearch()"
          size="small">
          重置
        </el-button>
      </div>
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
          <el-form-item label="账号：">
            <el-input v-model="listQuery.account" class="input-width" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item label="昵称：">
            <el-input v-model="listQuery.nickName" class="input-width" placeholder="昵称"></el-input>
          </el-form-item>
          <el-form-item label="真实姓名：">
            <el-input v-model="listQuery.realName" class="input-width" placeholder="真实姓名"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>用户列表</span>
      <el-button size="mini" class="btn-add" @click="handleAddUser()">添加</el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="userTable"
                :data="list"
                style="width: 100%;"
                @selection-change="handleSelectionChange"
                v-loading="listLoading" border>
<!--
        <el-table-column type="selection" width="280" align="center"></el-table-column>
-->
        <el-table-column hidden="hidden" label="编号" width="280" align="center" v-show="0">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="用户名" align="center" width="280">
          <template slot-scope="scope">{{scope.row.account}}</template>
        </el-table-column>
        <el-table-column label="注册时间" align="center" width="200">
          <template slot-scope="scope">{{scope.row.createTime}}</template>
        </el-table-column>
        <el-table-column label="cqc余额" width="120" align="center">
          <template slot-scope="scope">{{scope.row.cqcBalance}}</template>
        </el-table-column>
        <el-table-column label="状态" width="100" align="center">
          <template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
        </el-table-column>

        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="text"
                       @click="handleRecharge(scope.$index, scope.row)">充值
            </el-button>
            <el-button size="mini"
                       type="text"
                       @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>

            <el-button size="mini"
                       type="text"
                       @click="handleClose(scope.$index, scope.row)">封号
            </el-button>
            <el-button size="mini"
                       type="text"
                       @click="handleOpenUser(scope.$index, scope.row)">解封
            </el-button>



          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--
    <div class="batch-operate-container">
      <el-select
        size="small"
        v-model="operateType" placeholder="批量操作">
        <el-option
          v-for="item in operates"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button
        style="margin-left: 20px"
        class="search-button"
        @click="handleBatchOperate()"
        type="primary"
        size="small">
        确定
      </el-button>
    </div>
   -->
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes,prev, pager, next,jumper"
        :page-size="listQuery.pageSize"
        :page-sizes="[1,10,15]"
        :current-page.sync="listQuery.pageNum"
        :total="total">
      </el-pagination>
    </div>

    <el-dialog title="添加用户"
               :visible.sync="addDialogVisible"
               width="60%">

        <el-form :model="userAddDialogData" :rules="addUserRules" ref="userAddDialogData"
                 label-width="100px">
          <el-form-item label="用户名：" prop="account">
            <el-input name="account" v-model="userAddDialogData.account" style="width: 300px"></el-input>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input name="password"  v-model="userAddDialogData.password" style="width: 300px;"></el-input>
          </el-form-item>
          <el-form-item label="确认密码：" prop="confirmPassword">
            <el-input name="confirmPassword" v-model="userAddDialogData.confirmPassword" style="width: 300px"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer">
          <el-button @click="addDialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleSaveUser" size="small">确定保存</el-button>
        </span>

    </el-dialog>

    <el-dialog title="充值cqc"
               :visible.sync="rechargeDialogVisible"
               width="40%">

      <el-form :model="rechargeDialogData" :rules="rechargeRules" ref="rechargeDialogData"
               label-width="200px">

        <el-form-item label="id：" prop="userId">
          <el-input name="userId" readonly v-model="rechargeDialogData.userId" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="用户名：" prop="account">
          <el-input name="account" readonly  v-model="rechargeDialogData.account" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="充值cqc数量：" prop="cqc">
          <el-input name="cqc" v-model="rechargeDialogData.cqc" style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
          <el-button @click="rechargeDialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleConfirmRecharge" size="small">确定充值</el-button>
        </span>
    </el-dialog>


    <el-dialog title="禁封账号"
               :visible.sync="closeDialogVisible"
               width="40%">

      <el-form :model="closeDialogData" :rules="closeDialogRules" ref="closeDialogData"
               label-width="200px">

        <el-form-item label="用户id：" prop="userId">
          <el-input name="userId" readonly v-model="closeDialogData.userId" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="用户名：" prop="account">
          <el-input name="account" readonly  v-model="closeDialogData.account" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="截止封号时间：" prop="closeTime">
          <el-date-picker name="closeTime" v-model="closeDialogData.closeTime" type="date"
                          format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
                          onchange="dateChangebirthday"
                          placeholder="请选择时间">
          </el-date-picker>
          </el-form-item>
      </el-form>
      <span slot="footer">
          <el-button @click="closeDialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleConfirmClose" size="small">确定封号</el-button>
        </span>
    </el-dialog>

    <el-dialog title="费率设置"
               :visible.sync="setRateVisible"
               width="40%">
      <!-- 里面放一个form表单加表格 -->
      <el-form :model="setRateDialogData"  ref="setRateDialogData"
               label-width="200px">
        <el-form-item label="用户id：" prop="userId">
          <el-input name="userId" readonly v-model="setRateDialogData.userId" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="用户名：" prop="account">
          <el-input name="account" readonly  v-model="setRateDialogData.account" style="width: 300px"></el-input>
        </el-form-item>
        <el-table :data="rateList" v-loading="rateListLoading" border>
          <el-table-column  label="收款渠道" width="200" align="center" >
            <template slot-scope="scope">
              {{scope.row.channel | formatChannel}}
            </template>
          </el-table-column>
          <el-table-column label="费率" align="center" width="300">
            <template slot-scope="scope">
              <el-input name="userId"  v-model="scope.row.rate" style="width: 200px"></el-input>
            </template>
          </el-table-column>
        </el-table>
      </el-form>

      <span slot="footer">
          <el-button @click="setRateVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleSetUserRate" size="small">确定设置</el-button>
        </span>

    </el-dialog>



  </div>
</template>
<script>
  import {
    fetchList,
    recharge, add, delUser, close,open,
    getUserRateList,setUserRate
  } from '@/api/user';
  //import {fetchList as fetchSubjectList} from '@/api/msg.js';
  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    account: null,
    nickName: null,
    realName: null
  };
  const defaultUserDialogData = {
    account : null,
    password: null,
    confirmPassword: null
  };
  const defaultRecommendOptions = [
    {
      label: '佣金加成',
      value: 1
    }
  ];
  export default {
    name: 'faqList',
    data() {
      const validateAccount = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入用户名'))
        } else {
          callback()
        }
      };
      const validatePassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入密码'))
          return;
        } if (value.length < 6) {
          callback(new Error('密码长度必须大约6'))
        } else {
          callback()
        }
      };
      const validateConfirmPassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入确认密码'))
        }  else {
          callback()
        }
      };
      const validateCqc = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入数量'))
        }
        value = Number(value);
        if (typeof value === 'number' && !isNaN(value)) {
          if (value <= 0) {
            callback(new Error('最小为1'))
          } else {
            callback()
          }
        } else {
          callback(new Error('请输入数字'))
        }
      };
      return {
        listQuery: Object.assign({}, defaultListQuery),
        recommendOptions: Object.assign({}, defaultRecommendOptions),
        list: null,
        total: null,
        listLoading: false,
        multipleSelection: [],
        operates: [
          {
            label: "删除",
            value: 2
          }
        ],
        operateType: null,
        rechargeDialogVisible: false,
        rechargeDialogData: {
            userId : '',
            cqc: 0
        },
        rechargeRules: {
          cqc:[{required: true, trigger: 'blur', validator: validateCqc}]
        },
        addDialogVisible: false,
        userAddDialogData: Object.assign({}, defaultUserDialogData),
        addUserRules: {
          account:[{required: true, trigger: 'blur', validator: validateAccount}],
          password:[{required: true, trigger: 'blur', validator: validatePassword}],
          confirmPassword:[{required: true, trigger: 'blur', validator: validateConfirmPassword}]
        },
        // 封号处理
        closeDialogVisible : false,
        closeDialogData : {},
        closeDialogRules : {
          closeTime:[{required: true, message:"请选择时间"}]
        },
        // 费率设置
        rateList : [],
        rateListLoading:false,
        setRateVisible:false,
        setRateDialogData:{}
      }
    },
    created() {
      this.getList();
    },
    filters: {
      formatType(value) {

      },
      formatStatus(status) {
        if (status === 1) {
          return '正常';
        } else if (status == 0) {
          return '锁定';
        } else if (status == 2) {
          return '封禁';
        }else {
          return '无效';
        }
      },
      formatChannel(channel){
        if (channel === 1){
          return '支付宝';
        }else if (channel == 2) {
          return '微信';
        }else {
          return '未知';
        }
      },
      formatTime(time) {
        if (time == null || time === '') {
          return 'N/A';
        }
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
      },
    },
    methods: {
      handleResetSearch() {
        this.listQuery = Object.assign({}, defaultListQuery);
      },
      handleSearchList() {
        this.listQuery.pageNum = 1;
        this.getList();
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getList();
      },
      handleDelete(index, row) {
        this.deleteUser(row.id);
      },
      handleBatchOperate() {
        if (this.multipleSelection < 1) {
          this.$message({
            message: '请选择一条记录',
            type: 'warning',
            duration: 1000
          });
          return;
        }
        let ids = [];
        for (let i = 0; i < this.multipleSelection.length; i++) {
          ids.push(this.multipleSelection[i].id);
        }
        if (this.operateType === 2) {
          //删除
          this.deleteUser(ids);
        } else {
          this.$message({
            message: '请选择批量操作类型',
            type: 'warning',
            duration: 1000
          });
        }
      },
      handleAddUser(){
        this.addDialogVisible = true;
        this.userAddDialogData = Object.assign({}, defaultUserDialogData);
      },
      handleRecharge(index, row) {
        this.rechargeDialogVisible = true;
        this.rechargeDialogData.userId = row.id;
        this.rechargeDialogData.account = row.account;
        this.rechargeDialogData.cqc = 0.00;
      },
      handleClose(index, row){
        this.closeDialogVisible = true;
        this.closeDialogData.userId = row.id;
        this.closeDialogData.account = row.account;
      },
      handleOpenUser(index, row){
        this.openUser(row.id);
      },
      handleSaveUser() {
        this.$refs.userAddDialogData.validate(valid => {
          if (valid) {
            add(this.userAddDialogData).then(response => {
              this.addDialogVisible = false;
              this.getList();
              this.$message({
                type: 'success',
                message: '添加用户成功!'
              });
            });
          } else {
            console.log('参数验证不合法！');
            return false
          }
        });
      },
      handleConfirmRecharge(){
        // 确认充值处理
        this.$refs.rechargeDialogData.validate(valid => {
          if (valid) {
            recharge(this.rechargeDialogData).then(response => {
              this.rechargeDialogVisible = false;
              this.getList();
              this.$message({
                type: 'success',
                message: '充值成功!'
              });
            });
          } else {
            console.log('参数验证不合法！');
            return false
          }
        });
      },
      dateChangebirthday(val){
        this.closeDialogData.closeTime = val;
      },
      handleConfirmClose(){
        this.$refs.closeDialogData.validate(valid => {
          if (valid) {
            close(this.closeDialogData).then(response => {
              this.closeDialogVisible = false;
              this.getList();
              this.$message({
                type: 'success',
                message: '处理成功!'
              });
            });
          } else {
            console.log('参数验证不合法！');
            return false
          }
        });
      },


      getList() {
        this.listLoading = true;
        fetchList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data.records;
          this.total = response.data.total;
        })
      },
      deleteUser(ids) {
        this.$confirm('是否要删除该用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delUser(ids).then(response => {
            this.getList();
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          });
        })
      },
      openUser(ids) {
        this.$confirm('是否解封该用户?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          open(ids).then(response => {
            this.getList();
            this.$message({
              type: 'success',
              message: '解封成功!'
            });
          });
        })
      },


      handleViewRateDialog(index, row){
        this.setRateVisible = true;
        this.setRateDialogData.userId = row.id;
        this.setRateDialogData.account = row.account;
        // 调用方法获取用户的费率数据
        getUserRateList(row.id).then(response => {
          this.rateListLoading = false;
          this.rateList=response.data;
        });


      },
      handleSetUserRate(){
        // 保存费率设置
        let data = {
          "userId": this.setRateDialogData.userId,
          "rateList":this.rateList
        }
        setUserRate(data).then(response => {
          this.setRateVisible = false;
        });

      }

    }
  }
</script>
<style></style>
