package xyz.markapp.markmvvmtest1.model

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName

class User() {

    @SerializedName("login")
    var login: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("company")
    var company: String? = null

    @SerializedName("blog")
    var blog: String? = null

    @SerializedName("location")
    var location: String? = null

    @SerializedName("avatar_url")
    var avatar_url: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("followers")
    var followers: Int? = 0

    @SerializedName("following")
    var following: Int? = 0


    companion object {

        @JvmStatic
        @BindingAdapter("followers")
        fun getFollowers(view: TextView, user: User?) {
            var txt: String = user?.followers.toString() + " followers. / " + user?.following + " following";
            view.setText(txt)
        }

        @JvmStatic
        @BindingAdapter("name")
        fun get_name(view: TextView, name: String?) {
            var txt: String = name + ""
            view.setText(txt)
        }

        @JvmStatic
        @BindingAdapter("loginname")
        fun get_loginname(view: TextView, loginname: String?) {
            var txt: String = loginname + ""
            view.setText(txt)
        }

        @JvmStatic
        @BindingAdapter("email")
        fun get_email(view: TextView, email: String?) {
            var txt: String = email ?: "email" + ""

            view.setText(txt)
        }
    }
}

/* //users/xxx:  persion information
   {
 "login": "jvantuyl",
 "id": 101,
 "node_id": "MDQ6VXNlcjEwMQ==",
 "avatar_url": "https://avatars.githubusercontent.com/u/101?v=4",
 "gravatar_id": "",
 "url": "https://api.github.com/users/jvantuyl",
 "html_url": "https://github.com/jvantuyl",
 "followers_url": "https://api.github.com/users/jvantuyl/followers",
 "following_url": "https://api.github.com/users/jvantuyl/following{/other_user}",
 "gists_url": "https://api.github.com/users/jvantuyl/gists{/gist_id}",
 "starred_url": "https://api.github.com/users/jvantuyl/starred{/owner}{/repo}",
 "subscriptions_url": "https://api.github.com/users/jvantuyl/subscriptions",
 "organizations_url": "https://api.github.com/users/jvantuyl/orgs",
 "repos_url": "https://api.github.com/users/jvantuyl/repos",
 "events_url": "https://api.github.com/users/jvantuyl/events{/privacy}",
 "received_events_url": "https://api.github.com/users/jvantuyl/received_events",
 "type": "User",
 "site_admin": false,
 "name": "Jayson Vantuyl",
 "company": null,
 "blog": "http://souja.net",
 "location": "San Francisco, California, USA",
 "email": null,
 "hireable": true,
 "bio": null,
 "twitter_username": "jvantuyl",
 "public_repos": 61,
 "public_gists": 15,
 "followers": 40,
 "following": 12,
 "created_at": "2008-01-30T01:11:50Z",
 "updated_at": "2021-11-01T22:33:07Z"
}
    */
