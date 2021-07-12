<template>
  <div class="app-container">
      <!-- 查询条件 -->
    <div class="filter-container">
      <el-input v-model="listQuery.word" placeholder="词语" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.result" placeholder="听写结果" clearable style="width: 90px" class="filter-item">
        <el-option v-for="item in resultOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />
      </el-select>

<!--      <el-select v-model="listQuery.type" placeholder="Type" clearable class="filter-item" style="width: 130px">-->
<!--        <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />-->
<!--      </el-select>-->
<!--      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">-->
<!--        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />-->
<!--      </el-select>-->
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-check" @click="handleBatchUpdate(1)">批量修改为正确</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-close" @click="handleBatchUpdate(0)">批量修改为错误</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-close" @click="handleBatchUpdate(99)">批量删除</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-close" @click="handleArchive">归档</el-button>

<!--      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">-->
<!--        Add-->
<!--      </el-button>-->
<!--      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">-->
<!--        Export-->
<!--      </el-button>-->
<!--      <el-checkbox v-model="showReviewer" class="filter-item" style="margin-left:15px;" @change="tableKey=tableKey+1">-->
<!--        reviewer-->
<!--      </el-checkbox>-->
    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%"
              @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>

      <el-table-column align="center" label="id" width="200" v-if="false">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

<!--      <el-table-column width="180px" align="center" label="Date">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column width="200px" align="center" label="时间">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="分组编号">
        <template slot-scope="scope">
          <span>{{ scope.row.groupId }} </span>
        </template>
      </el-table-column>

      <el-table-column width="350px" align="center" label="题目">
        <template slot-scope="scope">
          <span>{{ scope.row.topic }}</span>
        </template>
      </el-table-column>

      <el-table-column label="听写结果" class-name="status-col" width="100">
        <template slot-scope="scope">
          <el-tag :type="scope.row.result | statusFilter">
            {{ scope.row.result | resultFilter }}
          </el-tag>
        </template>
      </el-table-column>


      <el-table-column width="120px" align="center" label="描述">
        <template slot-scope="scope">
          <span>{{ scope.row.des }}</span>
        </template>
      </el-table-column>

<!--      <el-table-column width="100px" label="Importance">-->
<!--        <template slot-scope="scope">-->
<!--          <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" />-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--      <el-table-column class-name="status-col" label="Status" width="110">-->
<!--        <template slot-scope="{row}">-->
<!--          <el-tag :type="row.status | statusFilter">-->
<!--            {{ row.status }}-->
<!--          </el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--      <el-table-column min-width="300px" label="Title">-->
<!--        <template slot-scope="{row}">-->
<!--          <router-link :to="'/example/edit/'+row.id" class="link-type">-->
<!--            <span>{{ row.title }}</span>-->
<!--          </router-link>-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--      <el-table-column align="center" label="Actions" width="120">-->
<!--        <template slot-scope="scope">-->
<!--          <router-link :to="'/example/edit/'+scope.row.id">-->
<!--            <el-button type="primary" size="small" icon="el-icon-edit">-->
<!--              Edit-->
<!--            </el-button>-->
<!--          </router-link>-->
<!--        </template>-->
<!--      </el-table-column>-->

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getList" />
  </div>
</template>

<script>
import { fetchListTmp, batchUpdate , archive } from '@/api/question-his'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import { parseTime } from '@/utils'

const resultOptions = [
  { key: 1, display_name: '正确' },
  { key: 0, display_name: '错误' }
]

const resultTypeKeyValue = resultOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})


export default {
  name: 'QuestionTmpList',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        0: 'danger'
      }
      return statusMap[status]
    },
    resultFilter(type) {
      return resultTypeKeyValue[type]
    }
  },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      // 批量选择
      multipleSelection: [],
      resultOptions,
      listQuery: {
        result: undefined,
        word: undefined,
        pageNum: 1,
        pageSize: 20
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchListTmp(this.listQuery).then(response => {
        this.list = response.items
        this.total = response.total
        this.listLoading = false
      })
    },
    // 搜索
    handleFilter() {
      this.listQuery.pageNum = 1
      this.getList()
    },

    //  在table中添加selection-change的处理函数,回调函数可以拿到选中的数组
    handleSelectionChange (val) {
      this.multipleSelection = val
    },

    // 批量操作
    handleBatchUpdate(e) {
      if (!this.multipleSelection || this.multipleSelection.length < 1) {
        this.$message({ type: 'error', message: '请选择要处理的记录' })
        return
      }
      this.$confirm('即将批量修改/删除正确所选记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const idsList = []
        this.multipleSelection.forEach((item, index) => {
          idsList.push(item.id)
        })
        const param = {
          ids: idsList,
          result: e
        }
        batchUpdate(param).then((response) => {
          var message = '操作成功!'
          this.$message({
            type: 'success',
            message: message
          })
          this.getList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消批量操作'
        })
      })
    },



    // 搜索
    handleArchive() {
      if (!this.multipleSelection || this.multipleSelection.length < 1) {
        this.$message({ type: 'error', message: '请选择要处理的记录' })
        return
      }

      this.$confirm('即将归档所选记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const idsList = []
        this.multipleSelection.forEach((item, index) => {
          idsList.push(item.id)
        })
        const param = {
          ids: idsList
        }
        archive(param).then((response) => {
          var message = '操作成功!'
          this.$message({
            type: 'success',
            message: message
          })
          this.getList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        })
      })
    }

  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
