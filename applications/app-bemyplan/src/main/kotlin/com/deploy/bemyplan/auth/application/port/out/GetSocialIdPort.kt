package com.deploy.bemyplan.auth.application.port.out

interface GetSocialIdPort {
    fun getSocialId(query: GetSocialIdQuery): String
}