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
          <el-form-item label="标题：">
            <el-input v-model="listQuery.title" class="input-width" placeholder="标题"></el-input>
          </el-form-item>
          <el-form-item label="消息类型：">
            <el-select v-model="listQuery.type" placeholder="全部" clearable class="input-width">
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
      <span>数据列表</span>
      <el-button size="mini" class="btn-add" @click="handleAddMsg()">添加消息</el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="newSubjectTable"
                :data="list"
                style="width: 100%;"
                @selection-change="handleSelectionChange"
                v-loading="listLoading" border>
        <el-table-column type="selection" width="60" align="center"></el-table-column>
        <el-table-column hidden="hidden" label="编号" width="280" align="center" v-show="0">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="标题" align="center" width="200">
          <template slot-scope="scope">{{scope.row.title}}</template>
        </el-table-column>
        <el-table-column label="内容" align="center" width="300">
          <template slot-scope="scope">{{scope.row.content}}</template>
        </el-table-column>
        <el-table-column label="消息类型" width="150" align="center">
          <template slot-scope="scope">
            {{scope.row.type | formatType}}
          </template>
        </el-table-column>
        <el-table-column label="状态" width="80" align="center">
          <template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
        </el-table-column>
        <el-table-column label="操作" width="100" align="center">
          <template slot-scope="scope">
            <!--<el-button size="mini"
                       type="text"
                       @click="handleEditSort(scope.$index, scope.row)">编辑
            </el-button>-->
            <el-button size="mini"
                       type="text"
                       @click="handleDelete(scope.$index, scope.row)">删除
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

    <el-dialog title="添加消息"
               :visible.sync="addDialogVisible"
               width="60%">

        <el-form :model="msgDialogData" :rules="msgRules" ref="msgDialogData"
                 label-width="100px">
          <el-form-item label="标题：" prop="title">
            <el-input name="title" v-model="msgDialogData.title" style="width: 500px"></el-input>
          </el-form-item>
          <el-form-item label="内容：" prop="content">
            <el-input name="content" v-model="msgDialogData.content" style="width: 500px"></el-input>
          </el-form-item>
          <el-form-item label="消息类型：" prop="type">
            <el-select name="type" v-model="msgDialogData.type" placeholder="全部" clearable class="input-width">
              <el-option v-for="item in recommendOptions"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer">
          <el-button @click="addDialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleUpdateSort" size="small">确定保存</el-button>
        </span>

    </el-dialog>

    <el-dialog title="编辑消息"
               :visible.sync="editDialogVisible"
               width="60%">

      <el-form :model="msgDialogData" :rules="msgRules" ref="msgDialogData"
               label-width="100px">
        <el-form-item label="标题：" prop="title">
          <el-input name="title" v-model="msgDialogData.title" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="内容：" prop="content">
          <el-input name="content" v-model="msgDialogData.content" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="消息类型：" prop="type">
          <el-select name="type" v-model="msgDialogData.type" placeholder="全部" clearable class="input-width">
            <el-option v-for="item in recommendOptions"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer">
          <el-button @click="editDialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleUpdateSort" size="small">确定更改</el-button>
        </span>

    </el-dialog>

  </div>
</template>
<script>
  import {
    fetchList,
    delMsg, add
  } from '@/api/msg';
  //import {fetchList as fetchSubjectList} from '@/api/msg.js';
  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    title: null,
    type: null
  };
  const defaultMsgDialogData = {
    id : null,
    title: null,
    type: null,
    content: null
  }
  const defaultRecommendOptions = [
    {
      label: '佣金加成',
      value: 1
    },
    {
      label: '全天佣金加成',
      value: 2
    },
    {
      label: '重要通知',
      value: 3
    },
    {
      label: '夜间佣金加成',
      value: 4
    },
    {
      label: '全天加成',
      value: 5
    },
    {
      label: '夜间活动',
      value: 6
    },
    {
      label: '温馨提示',
      value: 7
    }
  ];
  export default {
    name: 'msgList',
    data() {
      const validateTitle = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入标题'))
        } else {
          callback()
        }
      };
      const validateContent = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入内容'))
        } else {
          callback()
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
        editDialogVisible: false,
        editDialog: {

        },
        addDialogVisible: false,
        msgDialogData: Object.assign({}, defaultMsgDialogData),
        msgRules: {
          title:[{required: true, trigger: 'blur', validator: validateTitle}],
          content:[{required: true, trigger: 'blur', validator: validateContent}]
        }
      }
    },
    created() {
      this.getList();
    },
    filters: {
      formatType(value) {
        if (value === 1) {
          return '佣金加成';
        } else if (value === 2) {
          return '全天佣金加成';
        } else if (value === 3) {
          return '重要通知';
        } else if (value === 4) {
          return '夜间佣金加成';
        } else if (value === 5) {
          return '全天加成';
        } else if (value === 6) {
          return '夜间活动';
        } else if (value === 7) {
          return '温馨提示';
        } else if (value === 8) {
          return '爆单提醒';
        } else if (value === 9) {
          return '同城派单提醒';
        } else if (value === 10) {
          return '充值注意事项';
        } else if (value === 11) {
          return '信用积分说明';
        } else {
          return '类型未知';
        }
      },
      formatStatus(status) {
        if (status === 1) {
          return '有效';
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
      handleDelete(index, row) {
        this.deleteMsg(row.id);
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
          this.deleteMsg(ids);
        } else {
          this.$message({
            message: '请选择批量操作类型',
            type: 'warning',
            duration: 1000
          });
        }
      },
      handleAddMsg(){
        this.addDialogVisible = true;
        this.msgDialogData = Object.assign({}, defaultMsgDialogData);
      },
      handleEditSort(index, row) {
        this.editDialogVisible = true;
        this.msgDialogData.title = row.title;
        this.msgDialogData.content = row.content;
        this.msgDialogData.type = row.type;
      },
      handleUpdateSort() {

        // 验证表单是否填写完整

        this.$refs.msgDialogData.validate(valid => {
          if (valid) {
            add(this.msgDialogData).then(response => {
              this.addDialogVisible = false;
              this.getList();
              this.$message({
                type: 'success',
                message: '删除成功!'
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
      deleteMsg(ids) {
        this.$confirm('是否要删除该消息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = new URLSearchParams();
          params.append("id", ids);
          delMsg(params).then(response => {
            this.getList();
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          });
        })
      },
      getDialogList() {
        fetchSubjectList(this.dialogData.listQuery).then(response => {
          this.dialogData.list = response.data.list;
          this.dialogData.total = response.data.total;
        })
      }
    }
  }
</script>
<style></style>
