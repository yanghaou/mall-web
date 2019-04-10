1、类型保存

方式: POST

路径: /api/type

参数：

### Headers：

| 参数名称     | 参数值           | 是否必须 | 示例 | 备注 |
| :----------- | :--------------- | :------- | :--- | :--- |
| Content-Type | application/json |          |      |      |

Body:

```json
{
    "id":-1,//新建时传-1，编辑时传对应的id
    "name":"xx",//名称
    "order":1,//排序
    "unit":"个"，//单位
}
```

