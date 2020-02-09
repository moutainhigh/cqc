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

        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>FAQ常见问题列表</span>
      <el-button size="mini" class="btn-add" @click="handleAddMsg()">添加</el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="faqTable"
                :data="list"
                style="width: 100%;"
                @selection-change="handleSelectionChange"
                v-loading="listLoading" border>
        <el-table-column type="selection" width="60" align="center"></el-table-column>
        <el-table-column hidden="hidden" label="编号" width="0" align="center" v-show="0">
          <template slot-scope="scope">{{scope.row.id}}</template>
        </el-table-column>
        <el-table-column label="标题" align="center" width="300">
          <template slot-scope="scope">{{scope.row.title}}</template>
        </el-table-column>
        <el-table-column label="内容" align="center" width="600">
          <template slot-scope="scope">{{scope.row.content}}</template>
        </el-table-column>
        <el-table-column label="排序" width="50" align="center">
          <template slot-scope="scope">{{scope.row.displayOrder}}</template>
        </el-table-column>
        <el-table-column label="操作" width="80" align="center">
          <template slot-scope="scope">
            <el-button size="mini"
                       type="text"
                       @click="handleEditSort(scope.$index, scope.row)">编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
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

    <el-dialog title="添加FAQ问题"
               :visible.sync="addDialogVisible"
               width="60%">

        <el-form :model="faqDialogData" :rules="faqRules" ref="faqDialogData"
                 label-width="100px">
          <el-form-item label="标题：" prop="title">
            <el-input name="title" v-model="faqDialogData.title" style="width: 600px"></el-input>
          </el-form-item>
          <el-form-item label="内容：" prop="content">
            <el-input name="content" type="textarea" :autoSize="true" v-model="faqDialogData.content" style="width: 600px;"></el-input>
          </el-form-item>
          <el-form-item label="排序：" prop="displayOrder">
            <el-input name="displayOrder" v-model="faqDialogData.displayOrder" style="width: 200px"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer">
          <el-button @click="addDialogVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="handleUpdateSort" size="small">确定保存</el-button>
        </span>

    </el-dialog>

    <el-dialog title="编辑FAQ问题"
               :visible.sync="editDialogVisible"
               width="60%">

      <el-form :model="faqDialogData" :rules="faqRules" ref="faqDialogData"
               label-width="100px">
        <el-form-item label="标题：" prop="title">
          <el-input name="title" v-model="faqDialogData.title" style="width: 600px"></el-input>
        </el-form-item>
        <el-form-item label="内容：" prop="content">

          <el-input name="content" :autoSize="true" type="textarea" v-model="faqDialogData.content" style="width: 600px"></el-input>
        </el-form-item>
        <el-form-item label="排序：" prop="displayOrder">
          <el-input name="displayOrder" v-model="faqDialogData.displayOrder" style="width: 200px"></el-input>
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
    edit, add
  } from '@/api/faq';
  //import {fetchList as fetchSubjectList} from '@/api/msg.js';
  import {formatDate} from '@/utils/date';

  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    title: null
  };
  const defaultFaqDialogData = {
    id : null,
    title: null,
    content: null,
    displayOrder: null
  }
  const defaultRecommendOptions = [
    {
      label: '佣金加成',
      value: 1
    }
  ];
  export default {
    name: 'faqList',
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
        faqDialogData: Object.assign({}, defaultFaqDialogData),
        faqRules: {
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
        this.deleteFaq(row.id);
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
          this.deleteFaq(ids);
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
        this.faqDialogData = Object.assign({}, defaultFaqDialogData);
      },
      handleEditSort(index, row) {
        this.editDialogVisible = true;
        this.faqDialogData.title = row.title;
        this.faqDialogData.content = row.content;
        this.faqDialogData.displayOrder = row.displayOrder;
      },
      handleUpdateSort() {
        this.$refs.faqDialogData.validate(valid => {
          if (valid) {
            add(this.faqDialogData).then(response => {
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
      deleteFaq(ids) {
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
      }

    }
  }
</script>
<style></style>
