package com.a2a.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustProfile(
    @SerializedName("placeOfBirth")
     var placeOfBirth: String? = "",
    @SerializedName("AddressCity")
    var AddressCity: String? = "",
    @SerializedName("AddressCountry")
    var AddressCountry: String? = "",
    @SerializedName("DocValidDate")
    var DocValidDate: String? = "",
    @SerializedName("detPrivateNationality")
    var detPrivateNationality: String? = "",
    @SerializedName("AName")
    var aName: String = "",
    @SerializedName("ANameShort")
    var aNameShort: String = "",
    @SerializedName("Address1")
    var address1: String = "",
    @SerializedName("Address2")
    var address2: String = "",
    @SerializedName("BirthDate")
    var birthDate: String = "",
    @SerializedName("DateBirth")
    var dateBirth: String = "",
    @SerializedName("Branch")
    var branch: String = "",
    @SerializedName("CID")
    var cID: Int = 0,
    @SerializedName("CustID")
    var custID: String = "",
    @SerializedName("CustImage")
    var custImage: String? = "",
    @SerializedName("CustMnemonic")
    var custMnemonic: String = "",
    @SerializedName("Password")
    var password: String = ""
    ,@SerializedName("PasswordOld")
    var passwordOld: String = "",
    @SerializedName("CustType")
    var custType: Int = 0,
    @SerializedName("DocNo")
    var docNo: String = "",
    @SerializedName("MobNo")
    var mobNo: String = "",
    @SerializedName("DocNo1")
    var docNo1: String = "",
    @SerializedName("EMail")
    var eMail: String = "",
    @SerializedName("EName")
    var eName: String = "",
    @SerializedName("ENameShort")
    var eNameShort: String = "",
    @SerializedName("ISOCode")
    var iSOCode: String = "",
    @SerializedName("Gender")
    var gender: Boolean = true,
    @SerializedName("ISOCode2")
    var iSOCode2: String = "",
    @SerializedName("LWPT")
    var lWPT: Boolean = false,
    @SerializedName("LWTD")
    var lWTD: Boolean = false,
    @SerializedName("Lang")
    var lang: String = "",
    @SerializedName("MobileNumber")
    var mobileNumber: String = "",
    @SerializedName("MobileNumberMasked")
    var mobileNumberMasked: String = "",
    @SerializedName("Nationality")
    var nationality: String = "",
    @SerializedName("NationalityID")
    var nationalityID: String? = "",
    @SerializedName("RID")
    var rID: Int = 0,
    @SerializedName("RepID")
    var repID: String? = "",
    @SerializedName("TermsAndCondition")
    var termsAndCondition: Int = 0,
    @SerializedName("LoanNumber")
    var loanNumber: String ="",
    @SerializedName("CarOwnerShip")
    var carOwnerShip: Boolean = false,
    @SerializedName("Occupation")
    var occupation: String = "",
    @SerializedName("MaritalStatus")
    var maritalStatus: String = "",
    @SerializedName("CustMnemonicOld")
    var custMnemonicOld: String = "",
    @SerializedName("CustMnemonicNew")
    var custMnemonicNew: String = ""

) : Parcelable