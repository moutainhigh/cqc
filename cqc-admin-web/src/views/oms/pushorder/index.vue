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
          <el-form-item label="订单状态：">
            <el-select v-model="listQuery.status" placeholder="全部" clearable class="input-width">
              <el-option v-for="item in recommendOptions"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>订单列表</span>
      <el-button size="mini" class="btn-add" @click="handleAddOrder()">手动放单</el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="faqTable"
                :data="list"
                style="width: 100%;"
                @selection-change="handleSelectionChange"
                v-loading="listLoading" border>
        <el-table-column type="selection" width="60" align="center"></el-table-column>
        <el-table-column hidden="hidden" label="编号" width="280" align="center" v-show="0">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="金额" align="center" width="100">
          <template slot-scope="scope">{{scope.row.amount}}</template>
        </el-table-column>
        <el-table-column label="发布者" align="center" width="180">
          <template slot-scope="scope">{{scope.row.publisher}}</template>
        </el-table-column>
        <el-table-column label="收款渠道" width="80" align="center">
          <template slot-scope="scope">{{scope.row.channel | formatChannel}}</template>
        </el-table-column>
        <el-table-column label="放单时间" width="180" align="center">
          <template slot-scope="scope">{{scope.row.createTime}}</template>
        </el-table-column>
        <el-table-column label="状态" width="100" align="center">
          <template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
        </el-table-column>
        <el-table-column label="抢单人账号" width="200" align="center">
          <template slot-scope="scope">{{scope.row.account}}</template>
        </el-table-column>
        <el-table-column label="抢单人收款码" width="160" align="center">
          <template slot-scope="scope">
            <el-button type="text"
              @click="handleViewDetail(scope.$index, scope.row)">点击查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

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

    <el-dialog title="手动放单"
               :visible.sync="addOrderDialogVisible"
               width="40%">

      <el-form :model="addOrderDialogData" :rules="addOrderRules" ref="addOrderDialogData"
               label-width="200px">

        <el-form-item label="放单者昵称" prop="publisher">
          <el-input name="publisher" v-model="addOrderDialogData.publisher" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="付款渠道：" prop="channel">
          <el-select name="channel" v-model="addOrderDialogData.channel" placeholder="全部" clearable class="input-width">
            <el-option v-for="item in payChannel"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="放单金额：" prop="amount">
          <el-input name="amount" v-model="addOrderDialogData.amount" style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="addOrderDialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleConfirmAddOrder" size="small">确定放单</el-button>
      </span>
    </el-dialog>

    <el-dialog title="收款二维码"
               :visible.sync="showReceiveCodeDialogVisible"
               width="30%">
      <el-card class="mine-layout">
        <div style="text-align: center">
          <img width="150px" height="150px" :src="receiveCodeImg">
        </div>
      </el-card>

      <span slot="footer">
        <el-button @click="showReceiveCodeDialogVisible = false" size="small">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  import {fetchList, push, audit} from '@/api/order';
  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10
  };
  const defaultUserDialogData = {
    account : null,
    password: null,
    confirmPassword: null
  };
  const defaultRecommendOptions = [
    {
      label: '放单中',
      value: -1
    },
    {
      label: '被抢',
      value: 0
    },
    {
      label: '已付款',
      value: 1
    },
    {
     //-1放单中 0 代付款 1已付款 2已入账 3订单取消 4异常 5已过期 6金额不符 7付款失败
      label: '已入账',
      value: 2
    },
    {
      label: '订单取消',
      value: 3
    },
    {
      label: '异常',
      value: 4
    },
    {
      label: '已过期',
      value: 5
    },
    {
      label: '金额不符',
      value: 6
    },
    {
      label: '付款失败',
      value: 7
    }
  ];
  export default {
    name: 'faqList',
    data() {
      const validatePublisher = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入发布者昵称'))
        } else {
          callback()
        }
      };
      const validateChannel = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请选择付款方式'))
        } else {
          callback()
        }
      };
      const validateAmount = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入放单金额'))
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
        payChannel: [
          {
            label: '支付宝',
            value: 1
          },
          {
            label: '微信',
            value: 2
          }
        ],
        operateType: null,
        addOrderDialogVisible: false,
        addOrderDialogData:{
          publisher:"",
          channel : 0,
          amount:0
        },
        addOrderRules: {
          publisher:[{required: true, trigger: 'blur', validator: validatePublisher}],
          channel:[{required: true, trigger: 'blur', validator: validateChannel}],
          amount:[{required: true, trigger: 'blur', validator: validateAmount}]
        },
        showReceiveCodeDialogVisible: false,
        receiveCodeImg: null
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
          return '已付款';
        } else if (status == 0) {
          return '被抢';
        } else if (status == -1) {
          return '放单中';
        } else if (status == 2) {
          return '已收款';
        } else if (status == 3) {
          return '订单取消';
        } else {
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
      handleAddOrder(){
        this.addOrderDialogVisible = true;
        this.addOrderDialogData = Object.assign({}, {});
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
          this.audit(ids);
        } else {
          this.$message({
            message: '请选择批量操作类型',
            type: 'warning',
            duration: 1000
          });
        }
      },
      handleViewDetail(index, row){
        this.showReceiveCodeDialogVisible = true;
        this.receiveCodeImg = row.receiveCodeImg;
      },
      getList() {
        this.listLoading = true;
        fetchList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data.records;
          this.total = response.data.total;
        })
      },
      handleConfirmAddOrder(){
        // 确认放单处理
        this.$refs.addOrderDialogData.validate(valid => {
          if (valid) {
            push(this.addOrderDialogData).then(response => {
              this.addOrderDialogVisible = false;
              this.getList();
              this.$message({
                type: 'success',
                message: '放单成功!'
              });
            });
          } else {
            console.log('参数验证不合法！');
            return false
          }
        });
      }
    }
  }
</script>
<style></style>
