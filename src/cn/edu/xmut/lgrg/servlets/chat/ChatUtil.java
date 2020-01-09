package cn.edu.xmut.lgrg.servlets.chat;

import cn.edu.xmut.lgrg.entity.PageData;
import cn.edu.xmut.lgrg.entity.SysCommodity;
import cn.edu.xmut.lgrg.entity.SysUser;
import cn.edu.xmut.lgrg.util.StringUtil;
import cn.edu.xmut.lgrg.util.UserUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: ZiNan
 * @Date: 2020/1/9 17:32
 * @Description:
 */
public class ChatUtil {
    final static Map<String, List<Chat>> chats = new HashMap<>();

    final static List<OnlineUser> online = new ArrayList<>();

    public PageData getChats(HttpServletRequest request) throws Exception {
        String userId = UserUtil.getUserId(request);
        if(StringUtil.isNull(userId)){
            throw new Exception("异常~");
        }
        PageData res = new PageData();
        boolean item = false;
//        List
//        for (int i = 0; i < ; i++) {
//
//        }

        return new PageData();
    }
    class Chat{
        private String content;
        private String fromUserId;
        private String toUserId;
        private String sendTime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(String fromUserId) {
            this.fromUserId = fromUserId;
        }

        public String getToUserId() {
            return toUserId;
        }

        public void setToUserId(String toUserId) {
            this.toUserId = toUserId;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }
    }

    class OnlineUser{
        private String userId;
        private long lastTime;
        private SysUser user;

        public SysUser getUser() {
            return user;
        }

        public void setUser(SysUser user) {
            this.user = user;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public long getLastTime() {
            return lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }
    }
}
