http://127.0.0.1:4444/api/generate?configName=1.txt
http://127.0.0.1:4444/api/generateConfigFile
http://127.0.0.1:4444/api/generateText

# 首次初始化 init=true
http://127.0.0.1:4444/api/v2/generate?name=default.txt&lyricTruncatedMode=1&init=1
# 歌词出来后二次处理：init=false; lyricTruncatedMode:1完成，2首字母截断，3首字母阶段，只保留第一阶段
http://127.0.0.1:4444/api/v2/generate?name=default.txt&lyricTruncatedMode=3&init=0

#
JsonPath文章：https://juejin.cn/post/6850418109473783816