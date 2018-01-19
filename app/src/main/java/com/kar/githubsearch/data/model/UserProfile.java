package com.kar.githubsearch.data.model;

/**
 * Created by Kemal Amru Ramadhan on 1/19/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("login")
    @Expose
    public String login;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;

    @SerializedName("gravatar_id")
    @Expose
    public String gravatarId;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("html_url")
    @Expose
    public String htmlUrl;

    @SerializedName("followers_url")
    @Expose
    public String followersUrl;

    @SerializedName("following_url")
    @Expose
    public String followingUrl;

    @SerializedName("gists_url")
    @Expose
    public String gistsUrl;

    @SerializedName("starred_url")
    @Expose
    public String starredUrl;

    @SerializedName("subscriptions_url")
    @Expose
    public String subscriptionsUrl;

    @SerializedName("organizations_url")
    @Expose
    public String organizationsUrl;

    @SerializedName("repos_url")
    @Expose
    public String reposUrl;

    @SerializedName("events_url")
    @Expose
    public String eventsUrl;

    @SerializedName("received_events_url")
    @Expose
    public String receivedEventsUrl;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("site_admin")
    @Expose
    public Boolean siteAdmin;

    @SerializedName("name")
    @Expose
    public Object name;

    @SerializedName("company")
    @Expose
    public Object company;

    @SerializedName("blog")
    @Expose
    public String blog;

    @SerializedName("location")
    @Expose
    public Object location;

    @SerializedName("email")
    @Expose
    public Object email;

    @SerializedName("hireable")
    @Expose
    public Object hireable;

    @SerializedName("bio")
    @Expose
    public Object bio;

    @SerializedName("public_repos")
    @Expose
    public Integer publicRepos;

    @SerializedName("public_gists")
    @Expose
    public Integer publicGists;

    @SerializedName("followers")
    @Expose
    public Integer followers;

    @SerializedName("following")
    @Expose
    public Integer following;

    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public Object getName() {
        return name;
    }

    public Object getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public Object getLocation() {
        return location;
    }

    public Object getEmail() {
        return email;
    }

    public Object getHireable() {
        return hireable;
    }

    public Object getBio() {
        return bio;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public Integer getPublicGists() {
        return publicGists;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
