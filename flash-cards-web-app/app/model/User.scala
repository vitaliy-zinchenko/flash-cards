package model

import com.mohiva.play.silhouette.api.Identity

/**
  * @author Vitaliy_Zinchenko
  */
case class User(id: Option[Long],
                firstName: Option[String],
                lastName: Option[String],
                email: Option[String],
                providerId: String,
                providerKey: String
               ) extends Identity
