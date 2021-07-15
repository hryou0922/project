<template>
  <div class="tab-container">
<!--    <el-tabs v-model="activeName" style="margin-top:15px" type="border-card" @tab-click="handleClick">-->
    <el-tabs v-model="activeName" style="margin-top:15px" type="border-card" >
      <el-tab-pane label="组列表" name="groupList">
        <group-list v-if="activeName == 'groupList'" @showBatchDetail="showBatchDetailTab" />
      </el-tab-pane>
      <el-tab-pane label="组题目列表" name="groupWordList">
        <group-word-list v-if="activeName == 'groupWordList'" :group-id="groupId" />
<!--        <group-word-list v-if="activeName == 'groupWordList'" :batch-id="batchId" :date-value="dateValues" />-->
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import groupList from './components/group-list'
  import groupWordList from './components/group-question-list'

  export default {
    name: 'gp-list',
    components: { groupList, groupWordList },
    data() {
      return {
        groupId: '',
        activeName: 'groupList'
      }
    },
    created() {
      // this.$store.dispatch('clearPageNum')
    },
    methods: {
      // handleClick(tab, event) {
      //   console.log(tab, event);
      //   if(tab.name == 'groupWordList'){
      //       // this.groupId = ''
      //   }
      // },
      showBatchDetailTab(id){
        this.groupId = id;
        this.activeName = 'groupWordList'
      }
    }
  }
</script>

<style scoped>
  .tab-container{
    margin: 30px;
  }
</style>
