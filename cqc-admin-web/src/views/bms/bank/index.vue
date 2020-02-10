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
          <el-form-item label="银行名称：">
            <el-input v-model="listQuery.name" class="input-width" placeholder="名称"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>银行列表</span>
      <el-button size="mini" class="btn-add" @click="handleAddBank()">添加</el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="bankTable"
                :data="list"
                style="width: 100%;"
                @selection-change="handleSelectionChange"
                v-loading="listLoading" border>
        <el-table-column type="selection" width="60" align="center"></el-table-column>
        <el-table-column hidden="hidden" label="id" width="280" align="center" v-show="0">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="银行名称" align="center" width="300">
          <template slot-scope="scope">{{scope.row.name}}</template>
        </el-table-column>
        <el-table-column label="银行logo" align="center" width="300">
          <template slot-scope="scope">
            <img style="height:50px" :src="scope.row.logo">
          </template>
        </el-table-column>

      </el-table>
    </div>


    <el-dialog title="添加银行"
               :visible.sync="addBankDialogVisible"
               width="60%">

      <el-form :model="addBankDialogData" :rules="addBankRules" ref="addBankDialogData"
               label-width="100px">
        <el-form-item label="银行名称：" prop="name">
          <el-input name="name" v-model="addBankDialogData.name" style="width: 300px"></el-input>
        </el-form-item>

        <el-form-item label="银行logo：" prop="logo">
          <single-upload v-model="addBankDialogData.logo"></single-upload>
        </el-form-item>

      </el-form>
      <span slot="footer">
          <el-button @click="addBankDialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleSaveBank" size="small">确定保存</el-button>
        </span>

    </el-dialog>

  </div>
</template>
<script>
  import {fetchList, add} from '@/api/bank';
  import SingleUpload from '@/components/Upload/singleUpload'
  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    name:null,
    pageNum: 1,
    pageSize: 10
  };
  export default {
    name: 'bank',
    components:{SingleUpload},
    data() {
      return {
        listQuery: Object.assign({}, defaultListQuery),
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
        addBankDialogVisible: false,
        addBankDialogData:{
          name: null,
          logo : null
        },
        addBankRules: {
          name:[
              {required: true, trigger: 'blur', message: '请输入银行名称'},
              {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
          ],
          logo: [
              {required: true, message: '请上传品牌logo', trigger: 'blur'}
          ]
        }
      }
    },
    created() {
      this.getList();
    },
    filters: {
      formatType(value) {

      },
      formatStatus(status) {
        if (status == 1) {
          return '正常';
        } else if (status == 0) {
          return '不可用';
        } else {
          return '无效';
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
      getList() {
        this.listLoading = true;
        fetchList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data;
        })
      },
      handleAddBank(){
        this.addBankDialogVisible = true;
        this.addBankDialogData.name = '';
        this.addBankDialogData.logo = '';
      },
      handleSaveBank(){
        this.$refs.addBankDialogData.validate(valid => {
          if (valid) {
            add(this.addBankDialogData).then(response => {
              this.addBankDialogVisible = false;
              this.getList();
              this.$message({
                type: 'success',
                message: '添加银行成功!'
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
