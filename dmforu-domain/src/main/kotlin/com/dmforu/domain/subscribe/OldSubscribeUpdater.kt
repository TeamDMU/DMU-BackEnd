package com.dmforu.domain.subscribe

@Deprecated("구버전 사용자를 위해 남겨둔 Updater 입니다.")
class OldSubscribeUpdater(
    private val subscribeReader: SubscribeReader,
    private val subscribeWriter: SubscribeWriter,
) {
    @Deprecated("구버전 사용자를 위해 남겨둔 메서드입니다.")
    fun subscribeDepartment(token: String, department: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.changeDepartment(department)
        subscribe.subscribeDepartment()

        subscribeWriter.write(subscribe)
    }

    @Deprecated("구버전 사용자를 위해 남겨둔 메서드입니다.")
    fun unsubscribeDepartment(token: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.unsubscribeDepartment()

        subscribeWriter.write(subscribe)
    }

    @Deprecated("구버전 사용자를 위해 남겨둔 메서드입니다.")
    fun subscribeKeywords(token: String, keywords: List<String>) {
        val subscribe = subscribeReader.findById(token)

        subscribe.changeKeywords(keywords)
        subscribe.subscribeKeyword()

        subscribeWriter.write(subscribe)
    }

    @Deprecated("구버전 사용자를 위해 남겨둔 메서드입니다.")
    fun unsubscribeKeywords(token: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.unsubscribeKeyword()

        subscribeWriter.write(subscribe)
    }
}