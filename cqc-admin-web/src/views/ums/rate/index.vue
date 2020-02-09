<template>
  <div class="detail-container">
    <el-form :model="rateData"  ref="rateData"
             label-width="200px">
      <el-table :data="rateList" v-loading="rateListLoading" border>
        <el-table-column  label="收款渠道" width="200">
          <template slot-scope="scope">
            {{scope.row.id | formatChannel}}
          </template>
        </el-table-column>
        <el-table-column label="费率" align="center" width="300">
          <template slot-scope="scope">
            <el-input name="rate" oninput="value=value.replace(/[^0-9.]/g,'')"  v-model="scope.row.rate" style="width: 200px"></el-input>
            %
          </template>
        </el-table-column>
      </el-table>
      <el-row>
        <el-col :span="2" class="table-cell" align="center">
          <el-button align="center" type="primary" size="medium"
                     @click="handleSetRate">保存设置</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import {fetchList, setRate } from '@/api/rate';

  export default {
    name: 'rate',
    data() {
      return {
        id: null,
        rateData:{},
        rateList: [],
        rateListLoading : false
      }
    },
    created() {
      this.getList();
    },
    filters: {
      formatChannel(channel){
        if (channel === "1"){
          return '支付宝';
        }else if (channel == "2") {
          return '微信';
        }else {
          return '未知';
        }
      }
    },
    methods: {
      handleSetRate(){
        // 保存费率设置
        setRate(this.rateList).then(response => {
          this.getList();
          this.$message({
            type: 'success',
            message: '设置成功!'
          });
        });

      },
      getList(){
        fetchList().then(response => {
          this.rateList = response.data;
          this.rateListLoading = false;
        });
      }
    }
  }
</script>
