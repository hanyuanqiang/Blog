package com.hyq.action;

import com.hyq.entity.LeaveWords;
import com.hyq.service.LeaveWordsService;
import com.hyq.util.PrintException;
import com.hyq.util.ResponseUtil;
import com.hyq.util.SendMailUtil;
import com.hyq.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/2.
 */
@Controller
public class LeaveWordsAction extends ActionSupport implements ServletRequestAware {

    @Resource
    private LeaveWordsService leaveWordsService;
    private HttpServletRequest request;

    private LeaveWords leaveWords;
    private int leaveWordsId;
    private List<LeaveWords> leaveWordsList;
    private LeaveWords s_leaveWords;
    private String rightPage;

    public String getRightPage() {
        return rightPage;
    }

    public void setRightPage(String rightPage) {
        this.rightPage = rightPage;
    }

    public LeaveWords getS_leaveWords() {
        return s_leaveWords;
    }

    public void setS_leaveWords(LeaveWords s_leaveWords) {
        this.s_leaveWords = s_leaveWords;
    }

    public LeaveWords getLeaveWords() {
        return leaveWords;
    }

    public void setLeaveWords(LeaveWords leaveWords) {
        this.leaveWords = leaveWords;
    }

    public int getLeaveWordsId() {
        return leaveWordsId;
    }

    public void setLeaveWordsId(int leaveWordsId) {
        this.leaveWordsId = leaveWordsId;
    }

    public List<LeaveWords> getLeaveWordsList() {
        return leaveWordsList;
    }

    public void setLeaveWordsList(List<LeaveWords> leaveWordsList) {
        this.leaveWordsList = leaveWordsList;
    }

    public String save()throws Exception{
        if (leaveWords==null|| StringUtil.isEmpty(leaveWords.getContent())){
            PrintException.print(ServletActionContext.getResponse(),"错误操作");
            return null;
        }
        leaveWords.setIp(getRemortIP(request));
        leaveWords.setCreateTime(new Date());
        leaveWordsService.save(leaveWords);
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(ServletActionContext.getResponse(), result);

        SendMailUtil.send("1255426342@qq.com","您的博客有新的留言","游客说："+leaveWords.getContent());
        SendMailUtil.send("15957196210@139.com","您的博客有新的留言","游客说："+leaveWords.getContent());

        return null;

    }

    public String backdelete()throws Exception{
        leaveWordsService.delete(leaveWordsService.getLeaveWordsById(leaveWordsId));
        return "deleteSuccess";
    }

    public String backlist()throws Exception{
        leaveWordsList = leaveWordsService.findLeaveWords(s_leaveWords);
        leaveWordsService.changeStatus(1);
        request.getSession().setAttribute("noReadLWCount",0);
        rightPage = "/background/leaveWordsManager.jsp";
        return "list";
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
