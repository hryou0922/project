<template>
  <div class="app-container">
      <!-- 查询条件 -->
    <div class="filter-container">

<!--      <el-select v-model="listQuery.grade" placeholder="年级" clearable style="width: 90px" class="filter-item">-->
<!--        <el-option v-for="item in gradeOptions" :key="item" :label="item+'年级'" :value="item" />-->
<!--      </el-select>-->
<!--      <el-input v-model="listQuery.article" placeholder="文章标题" style="width: 120px;" class="filter-item"  />-->
      <el-input v-model="listQuery.word" placeholder="词语" style="width: 120px;" class="filter-item"  />
      <el-select v-model="listQuery.levels" placeholder="熟练度级别" multiple   clearable style="width: 90px" class="filter-item">
        <el-option v-for="item in levelOptions" :key="item.key" :label="item.display_name" :value="item.key" />
      </el-select>

<!--      <el-select v-model="listQuery.type" placeholder="Type" clearable class="filter-item" style="width: 130px">-->
<!--        <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />-->
<!--      </el-select>-->
<!--      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">-->
<!--        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />-->
<!--      </el-select>-->
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search
      </el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handlePlay">
        录音播放
      </el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleStop">
        停止
      </el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleCreateTmpWordGroup">
        创建临时组
      </el-button>
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

      <el-table-column width="100px" align="center" label="词语">
        <template slot-scope="scope">
          <span>{{ scope.row.word }}</span>
        </template>
      </el-table-column>

      <el-table-column width="80px" align="center" label="听写次数">
        <template slot-scope="scope">
          <span>{{ scope.row.total }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="熟练度级别">
        <template slot-scope="scope">
          <el-tag :type="scope.row.level | levelStatusFilter">
            <span>{{ scope.row.level | levelFilter}}</span>
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="最后听写时间">
        <template slot-scope="scope">

            <span>{{ scope.row.levelTime }}</span>

        </template>
      </el-table-column>

      <el-table-column width="360px" align="center" label="录音文件路径">
        <template slot-scope="scope">
          <span>{{ scope.row.voiceFile }}</span>
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
import { fetchList, stop, play, createTmpWordGroup } from '@/api/word'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

const levelOptions = [
  { key: -3, display_name: '极度需要练习' },
  { key: -2, display_name: '需要练习' },
  { key: -1, display_name: '不及格' },
  { key: 0, display_name: '待测试' },
  { key: 1, display_name: '及格' },
  { key: 2, display_name: '良' },
  { key: 3, display_name: '优秀' }
]

const levelTypeKeyValue = levelOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'WordList',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    levelStatusFilter(status) {
      const levelStatusMap = {
        '3': 'success',
        '2': 'success',
        '1': 'info',
        '0': 'info',
        '-1': 'danger',
        '-2': 'danger',
        '-3': 'danger'
      }
      return levelStatusMap[status]
    },
    levelFilter(type) {
      return levelTypeKeyValue[type]
    }
  },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      levelOptions: levelOptions,
      // 批量选择
      multipleSelection: [],
      listQuery: {
        levels: undefined,
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
    //  在table中添加selection-change的处理函数,回调函数可以拿到选中的数组
    handleSelectionChange (val) {
      this.multipleSelection = val
    },

    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
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
    // 创建临时组
    handleCreateTmpWordGroup() {
      if (!this.multipleSelection || this.multipleSelection.length < 1) {
        this.$message({ type: 'error', message: '请选择要处理的记录' })
        return
      }

      this.$confirm('即将根据所选记录创建临时组, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const wordList = []
        this.multipleSelection.forEach((item, index) => {
          wordList.push(item.word)
        })
        const param = {
          wordList: wordList
        }
        createTmpWordGroup(param).then((response) => {
          var message = '操作成功!'
          this.$message({
            type: 'success',
            message: message
          })
          this.getList()
        })
      }).catch((e) => {
        console.log(e, 'catch')
        this.$message({
          type: 'info',
          message: '已取消操作'
        })
      })


    },
    // 播放
    handlePlay() {
      play(this.listQuery).then(response => {
        console.log("播放")
      })
    },
    // 停止
    handleStop() {
      stop(this.listQuery).then(response => {
        console.log("播放")
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
