package com.hyq.action;

import com.hyq.entity.Wisdom;
import com.hyq.service.WisdomService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3.
 */
@Controller
public class WisdomAction extends ActionSupport{

    @Resource
    private WisdomService wisdomService;

    private Wisdom wisdom;
    private int wisdomId;
    private List<Wisdom> wisdomList;
    private String rightPage;

    public String getRightPage() {
        return rightPage;
    }

    public void setRightPage(String rightPage) {
        this.rightPage = rightPage;
    }

    public List<Wisdom> getWisdomList() {
        return wisdomList;
    }

    public void setWisdomList(List<Wisdom> wisdomList) {
        this.wisdomList = wisdomList;
    }

    public Wisdom getWisdom() {
        return wisdom;
    }

    public void setWisdom(Wisdom wisdom) {
        this.wisdom = wisdom;
    }

    public int getWisdomId() {
        return wisdomId;
    }

    public void setWisdomId(int wisdomId) {
        this.wisdomId = wisdomId;
    }

    public String backsave()throws Exception{
        wisdomService.save(wisdom);
        return SUCCESS;
    }

    public String backlist()throws Exception{
        wisdomList = wisdomService.findWisdomList();
        rightPage = "/background/wisdomManager.jsp";
        return "list";
    }

    public String backdelete()throws Exception{
        wisdomService.delete(wisdomService.getWisdomById(wisdomId));
        return SUCCESS;
    }

    public String backChangeStatus()throws Exception{
        wisdomService.changeStatus(wisdomId);
        return SUCCESS;
    }
}
