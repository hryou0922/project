<template>
  <div class="app-container">
      <!-- 查询条件 -->
    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="文章标题" style="width: 220px;" class="filter-item"  />

      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>

    </div>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">

      <el-table-column width="280px" align="center" label="名称">
        <template slot-scope="scope">
          <span @click="toBatchDetailTab(scope.row.id)" class="link-type">{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="词语总数">
        <template slot-scope="scope">
          <span>{{ scope.row.wordTotal }}次</span>
        </template>
      </el-table-column>

      <el-table-column width="80px" align="center" label="及格率">
        <template slot-scope="scope">
          <el-tag :type="scope.row.passRate | rateFilter">
            <span>{{ scope.row.passRate }}%</span>
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column width="80px" align="center" label="良率">
        <template slot-scope="scope">
          <el-tag :type="scope.row.goodRate | rateFilter">
            <span>{{ scope.row.goodRate }}%</span>
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column width="80px" align="center" label="优秀率">
        <template slot-scope="scope">
          <el-tag :type="scope.row.excellentRate | rateFilter">
            <span>{{ scope.row.excellentRate }}%</span>
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="统计最后更新时间">
        <template slot-scope="scope">
          <span>{{ scope.row.resultTime }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="创建日期">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="描述">
        <template slot-scope="scope">
          <span>{{ scope.row.des }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            Edit
          </el-button>
          <el-button v-if="row.type=='2'" size="mini" type="danger" @click="handleDelete(row,$index)">
            删除
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getList" />
  </div>
</template>

<script>
import { fetchList, deleteWordGroup} from '@/api/word-group'
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
  name: 'GroupList',
  components: { Pagination },
  filters: {
    rateFilter(status) {
      if(status < 60){
        return 'danger';
      }else if(status < 90){
        return 'info';
      }else {
        return 'success';
      }
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
      gradeOptions: [2, 3],
      unitOptions: [1,2,3,4,5,6,7,8],
      listQuery: {
        name: undefined,
        pageNum: 1,
        pageSize: 5
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
    },
    // 删除
    handleDelete(row, index) {
      this.$confirm('是否删除所选记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {
          groupId : row.id
        }
        deleteWordGroup(param).then((response) => {
          var message = '操作成功!'
          this.$message({
            type: 'success',
            message: message
          })
          this.list.splice(index, 1)
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除操作'
        })
      })
    },


    // 切换tab时调用响应的方法
    toBatchDetailTab(id) {
      this.$emit('showBatchDetail', id,this.dateValues)
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
