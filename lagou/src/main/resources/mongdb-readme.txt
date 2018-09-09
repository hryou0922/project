聚合查询
{
  _id: '$workYear',
  verageQuantity: { $avg: "$salaryBegin" },
  verageEndQuantity: { $avg: "$salaryEnd" },

}