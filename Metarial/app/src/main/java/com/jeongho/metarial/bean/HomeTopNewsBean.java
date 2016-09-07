package com.jeongho.metarial.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeongho on 2016/7/7.
 */
public class HomeTopNewsBean {

    public List<TopNews> homePage = new LinkedList<>();

    public class TopNews{
        private int articleId;
        private String title;
        private String logo;
        private String createTime;

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }

}
