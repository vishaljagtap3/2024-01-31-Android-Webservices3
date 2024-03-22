package `in`.bitcode.webservices2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import `in`.bitcode.webservices2.databinding.UserViewBinding

class UsersAdapter(
    private val users : ArrayList<User>
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(){

    inner class UserViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val userViewBinding : UserViewBinding
        init {
            userViewBinding = UserViewBinding.bind(view)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_view, null)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userViewBinding.user = users[position]

        /*loadWebImageUrlToImageView(
            holder.userViewBinding.imgUser,
            users[position].avatar
        )*/

        /*Glide.with(holder.itemView)
            .load(users[position].avatar)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.userViewBinding.imgUser)*/
    }
}