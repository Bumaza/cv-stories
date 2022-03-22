package sk.bumaza.cvstories.extensions

import sk.bumaza.cvstories.entity.managment.Account
import sk.bumaza.cvstories.security.services.User

fun User.isOwner(account: Account?) : Boolean {
    if(account == null) return false
    if(account.id == null) return false
    if(id == null) return false
    return account.id == id
}

fun User.isNotOwner(account: Account?) = !isOwner(account)

fun User.isNotOwner(accountId: Long) = accountId != id
