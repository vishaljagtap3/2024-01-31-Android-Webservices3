package `in`.bitcode.webservices2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.bitcode.webservices2.R
import `in`.bitcode.webservices2.databinding.UserViewBinding
import `in`.bitcode.webservices2.models.User

class UsersAdapter(
    private val users : ArrayList<User>
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(){


    interface OnUserClickListener {
        fun onUserClick(position: Int, user: User)
    }
    var onUserClickListener : OnUserClickListener? = null

    inner class UserViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val userViewBinding : UserViewBinding
        init {
            userViewBinding = UserViewBinding.bind(view)

            view.setOnClickListener {
                onUserClickListener?.onUserClick(adapterPosition, users[adapterPosition])
            }
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