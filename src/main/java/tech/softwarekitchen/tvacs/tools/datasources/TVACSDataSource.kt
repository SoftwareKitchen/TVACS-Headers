package tech.softwarekitchen.tvacs.tools.datasources

import kotlin.reflect.KClass

interface TVACSDataStream<T>{
    fun tick()
    fun get(): T
    fun getName(): String
}
interface TVACSDataSource {
    fun tick(){
        getStreams().forEach(TVACSDataStream<*>::tick)
    }
    fun getStreams(): List<TVACSDataStream<*>>

    fun getStream(name: String): TVACSDataStream<*>{
        return getStreams().first{it.getName() == name}
    }
}

class TVACSStreamIdentifier(path: String){
    val source: String = path.split("->")[0]
    val stream: String = path.split("->")[1]
}

data class TVACSDataSourceConfigurationMapEntry(val name: String, val displayName: String, val type: String, val default: Any)

interface TVACSDataSourceFactory<T: TVACSDataSource>{
    fun create(configuration: Map<String, Any>): T
    fun getTypes(): Map<String, KClass<*>>
    fun getName(): String

    fun getConfigurationEntries(): List<TVACSDataSourceConfigurationMapEntry>
}
