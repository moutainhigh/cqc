<template>
  <div class="detail-container">
    <div style="margin-top: 20px">
      <svg-icon icon-class="marker" style="color: #606266"></svg-icon>
      <span class="font-small">收货人信息</span>
    </div>
    <div class="table-layout">
      <el-row>
        <el-col :span="6" class="table-cell-title">用户名</el-col>
        <el-col :span="6" class="table-cell-title">{{real.account}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="6" class="table-cell">真实姓名</el-col>
        <el-col :span="6" class="table-cell">{{real.realName}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="6" class="table-cell">身份证号码</el-col>
        <el-col :span="6" class="table-cell">{{real.idNumber}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="6" class="table-cell">qq</el-col>
        <el-col :span="6" class="table-cell">{{real.qq}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="6" class="table-cell">email</el-col>
        <el-col :span="6" class="table-cell">{{real.email}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="6" class="table-cell">手机号码</el-col>
        <el-col :span="6" class="table-cell">{{real.telephone}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="12" class="table-cell">
          <el-button type="primary" size="medium"
                     @click="handleNext('1')"
                     v-show="real.status===0">通过</el-button>
          <el-button type="primary" size="medium"
                     @click="handleNext('-1')"
                     v-show="real.status===0">不通过</el-button>
        </el-col>
      </el-row>
    </div>
  </div>

</template>

<script>
  import {detail,audit } from '@/api/userRealinfo';


  export default {
    name: 'orderDetail',
    data() {
      return {
        id: null,
        real: {}
      }
    },
    created() {
      this.id = this.$route.query.id;
      console.log("认证id" + this.id);
      detail(this.id).then(response => {
        this.real = response.data;
      });
    },
    methods: {
      handleNext(status){
        var txt = status == 1? "通过" : "不通过";
        this.$confirm('确定'+txt+'?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = new URLSearchParams();
          params.append("id", this.real.id);
          params.append("status", status);
          audit(params).then(response => {
            // 调回列表页面
            this.$message({
              type: 'success',
              message: '操作成功!'
            });
          });
        })
      }
    }
  }
</script>

<style scoped>
  .detail-container {
    width: 80%;
    padding: 20px 20px 20px 20px;
    margin: 20px auto;
  }

  .operate-container {
    background: #F2F6FC;
    height: 80px;
    margin: -20px -20px 0;
    line-height: 80px;
  }

  .operate-button-container {
    float: right;
    margin-right: 20px
  }

  .table-layout {
    margin-top: 20px;
    border-left: 1px solid #DCDFE6;
    border-top: 1px solid #DCDFE6;
  }

  .table-cell {
    height: 60px;
    line-height: 40px;
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    font-size: 14px;
    color: #606266;
    text-align: center;
    overflow: hidden;
  }

  .table-cell-title {
    border-right: 1px solid #DCDFE6;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px;
    background: #F2F6FC;
    text-align: center;
    font-size: 14px;
    color: #303133;
  }
</style>
