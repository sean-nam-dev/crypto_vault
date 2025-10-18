package com.sean.cryptovault.core.common

import com.sean.cryptovault.R

const val LOCALIZATION = "localization"
const val PIN = "pin"
const val SECRET_WORD = "secret_word"
const val ENTRANCE_START_DESTINATION = "entrance_start_destination"
const val BIOMETRIC_AUTHENTICATION = "biometric_authentication"
const val TRY_COUNTER = "try_counter"
const val ENTRY_TRIES = "5"
const val SEPARATOR = "<-SEPARATOR->"
const val SORT_TYPE = "sort_type"
const val SORT_BY_DATE = 0
const val SORT_BY_SERVICE = 1
const val SORT_BY_IDENTIFIER = 2
const val PASSWORD_IS_VISIBLE = "1"
const val PASSWORD_IS_NOT_VISIBLE = "0"
const val PASSWORD_STATUS = "password_status"
const val TRUE = "true"
const val FALSE = "false"
const val DARK_MODE = "dark_mode"
const val ENCRYPTION_KEY = "encryption_key"

val VAULT_IMAGE_HASHMAP: HashMap<String, Int> = hashMapOf(
    "Black" to R.drawable.logo_plain_black,
    "Blue" to R.drawable.logo_plain_blue,
    "Brown" to R.drawable.logo_plain_brown,
    "Gray" to R.drawable.logo_plain_gray,
    "Gray" to R.drawable.logo_plain_gray_blue,
    "Green" to R.drawable.logo_plain_green,
    "Magenta" to R.drawable.logo_plain_magenta,
    "Orange" to R.drawable.logo_plain_orange,
    "Pink" to R.drawable.logo_plain_pastel_pink,
    "Pink" to R.drawable.logo_plain_pink,
    "Purple" to R.drawable.logo_plain_purple,
    "Red" to R.drawable.logo_plain_red,
    "Sky blue" to R.drawable.logo_plain_sky_blue,
    "Brown" to R.drawable.logo_plain_brown,
    "Yellow" to R.drawable.logo_plain_yellow,

    "9gag" to R.drawable.logo_9gag,
    "Adobe" to R.drawable.logo_adobe,
    "Airbnb" to R.drawable.logo_airbnb,
    "Alibaba" to R.drawable.logo_alibaba,
    "AliExpress" to R.drawable.logo_aliexpress,
    "Amazon" to R.drawable.logo_amazon,
    "Apple" to R.drawable.logo_apple,
    "Baidu" to R.drawable.logo_baidu,
    "Bandcamp" to R.drawable.logo_bandcamp,
    "Booking.com" to R.drawable.logo_bookingcom,
    "Craigslist" to R.drawable.logo_craigslist,
    "Discord" to R.drawable.logo_discord,
    "Dropbox" to R.drawable.logo_dropbox,
    "eBay" to R.drawable.logo_ebay,
    "Etsy" to R.drawable.logo_etsy,
    "Evernote" to R.drawable.logo_evernote,
    "Facebook" to R.drawable.logo_facebook,
    "Foodpanda" to R.drawable.logo_foodpanda,
    "Forbes" to R.drawable.logo_forbes,
    "GitHub" to R.drawable.logo_github,
    "Google" to R.drawable.logo_google,
    "Hootsuite" to R.drawable.logo_hootsuite,
    "HubSpot" to R.drawable.logo_hubspot,
    "IMDb" to R.drawable.logo_imdb,
    "Instagram" to R.drawable.logo_instagram,
    "Kickstarter" to R.drawable.logo_kickstarter,
    "LinkedIn" to R.drawable.logo_linkedin,
    "Medium" to R.drawable.logo_medium,
    "Metacafe" to R.drawable.logo_metacafe,
    "Microsoft" to R.drawable.logo_microsoft,
    "Moz" to R.drawable.logo_moz,
    "Naver" to R.drawable.logo_naver,
    "Netflix" to R.drawable.logo_netflix,
    "OLX" to R.drawable.logo_olx,
    "Pinterest" to R.drawable.logo_pinterest,
    "Quora" to R.drawable.logo_quora,
    "Reddit" to R.drawable.logo_reddit,
    "RightMove" to R.drawable.logo_rightmove,
    "Salesforce" to R.drawable.logo_salesforce,
    "SEMrush" to R.drawable.logo_semrush,
    "Shopify" to R.drawable.logo_shopify,
    "Snapchat" to R.drawable.logo_snapchat,
    "SoundCloud" to R.drawable.logo_soundcloud,
    "Spotify" to R.drawable.logo_spotify,
    "Steam" to R.drawable.logo_steam,
    "SurveyMonkey" to R.drawable.logo_surveymonkey,
    "TED" to R.drawable.logo_ted,
    "TikTok" to R.drawable.logo_tiktok,
    "TripAdvisor" to R.drawable.logo_tripadvisor,
    "Twitch" to R.drawable.logo_twitch,
    "Udemy" to R.drawable.logo_udemy,
    "Vimeo" to R.drawable.logo_vimeo,
    "VK" to R.drawable.logo_vk,
    "WeChat" to R.drawable.logo_wechat,
    "Wikipedia" to R.drawable.logo_wikipedia,
    "WordPress" to R.drawable.logo_wordpress,
    "X" to R.drawable.logo_x,
    "Yahoo" to R.drawable.logo_yahoo,
    "Yandex" to R.drawable.logo_yandex,
    "Yelp" to R.drawable.logo_yelp,
    "Zoho" to R.drawable.logo_zoho,
    "Zoom" to R.drawable.logo_zoom,
    "Zoopla" to R.drawable.logo_zoopla
)

@Suppress("EnumEntryName")
enum class Language {
    en,
    ar,
    es,
    fr,
    hi,
    hy,
    ja,
    kk,
    ko,
    mn,
    pl,
    pt,
    ru,
    uk,
    uz,
    vi,
    zh
}
