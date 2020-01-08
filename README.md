# java_practice

#### 如果添加.gitignore文件配置之后，仍然无法忽略已配置好的文件，则可能是缓存问题导致的，解决方式：

`git rm -r --cached .`
*然后正常提交即可*



## 如果项目出现冲突较多

1. 保留自己电脑的文件， 新建另一个文件夹

2. 在新的文件夹里，拉取 git 上面的代码

3. 将自己电脑上的代码复制上去，并提交

4. 完美

            

## git 常用提交命令

1. 添加代码到本地仓库： 
    `git add .` *注意有个英文句号 `.`*
2. 提交代码到本地仓库：
    `git commit -m "提交备注信息"` *注意, -m 后面备注信息必填，否则无法提交*
3. 将代码提交到远程仓库
    1. 第一次提交： `git push -u origin master`
    
    2. 第二次开始： `git push`
    
       

## git 其他常用命令

1. 在本地克隆远程已存在的代码：`git clone xxxx` 其中， xxx为仓库地址

2. 新建空仓库后，将本地代码直接上传到空仓库：
    
    >1. 在本地代码根目录, 创建本地git仓库： `git init
    >
    >2. 将本地仓库与远程仓库关联： `git remote add origin xxxx` ， 其中，`xxxx`表示远程仓库地址
    >3. 执行上述常用提交命令。即： 
    >     `git add .`
    >     `git commit -m "备注信息"`
    >     `git push`
    
    
## 常见问题解决:

1. 报错：

   ```
    ! [rejected]        master -> master (fetch first)
   error: failed to push some refs to 'https://github.com/1450032191/java_practice.git'
   hint: Updates were rejected because the remote contains work that you do
   hint: not have locally. This is usually caused by another repository pushing
   hint: to the same ref. You may want to first integrate the remote changes
   hint: (e.g., 'git pull ...') before pushing again.
   hint: See the 'Note about fast-forwards' in 'git push --help' for details.
   
   ```

   解决：`git pull` 大法解决。 

   *由于远程仓库的代码比本地代码新，需要进行拉取后才能push*

   *因此建议每次提交代码前先拉取*




2. 待续：


