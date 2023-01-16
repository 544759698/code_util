# 1 vim
vim file1
# 1.1 命令模式
dd # 删除一行
# 1.2 底行模式
:set nu/nonu #显示/不显示行号
:/world (n N)  nohl # 查找world （前一个 后一个）取消高亮
:shift+g / gg # 到文件尾/头

# 2 awk 逗号分隔找出第二列所有值并去重
cat all_part| awk -F, '{print $2}'| sort -u > all_part_uni

# 3 sort
sort a.txt b.txt | uniq -d  # 将a.txt b.txt文件进行排序，uniq使得两个文件中的内容为唯一的，使用-d输出两个文件中次数大于1的内容，即是得到交集
sort a.txt b.txt | uniq  # 将a.txt b.txt文件进行排序，uniq使得两个文件中的内容为唯一的，即可得到两个文件的并集
sort a.txt b.txt b.txt | uniq -u  #将两个文件排序，最后输出a.txt b.txt b.txt文件中只出现过一次的内容，因为有两个b.txt所以只会输出只在a.txt出现过一次的内容，即是a.txt-b.txt差集

# 4 df du
df -h
du -h -d 1 具体目录 # 查看某目录下目录和文件大小