<template>
  <div class="app-container">
      <!-- 查询条件 -->
    <div class="filter-container">
      <el-input v-model="listQuery.article" placeholder="文章名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
<!--      <el-select v-model="listQuery.importance" placeholder="Imp" clearable style="width: 90px" class="filter-item">-->
<!--        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />-->
<!--      </el-select>-->
<!--      <el-select v-model="listQuery.type" placeholder="Type" clearable class="filter-item" style="width: 130px">-->
<!--        <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />-->
<!--      </el-select>-->
<!--      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">-->
<!--        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />-->
<!--      </el-select>-->
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        Search
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

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="id" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

<!--      <el-table-column width="180px" align="center" label="Date">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column width="120px" align="center" label="年级">
        <template slot-scope="scope">
          <span>{{ scope.row.grade }} 年级</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="单元">
        <template slot-scope="scope">
          <span>{{ scope.row.unit }} 单元</span>
        </template>
      </el-table-column>

      <el-table-column width="240px" align="center" label="文章标题">
        <template slot-scope="scope">
          <span>{{ scope.row.article }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="词语">
        <template slot-scope="scope">
          <span>{{ scope.row.word }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="录音文件路径">
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
import { fetchList } from '@/api/word'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

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
    }
  },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        grade: undefined,
        unit: undefined,
        article: undefined,
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
