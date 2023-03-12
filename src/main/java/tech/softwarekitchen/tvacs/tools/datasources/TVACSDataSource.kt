package tech.softwarekitchen.tvacs.tools.datasources

import kotlin.reflect.KClass

interface TVACSDataSource<T> {
    fun tick()
    fun get(): T
}

interface TVACS2DDataPointSeriesDataSource: TVACSDataSource<DSTypeXYDataPoint>

interface TVACSDataSourceFactory<T: TVACSDataSource<*>>{
    fun create(): T
    fun getType(): KClass<*>
    fun getName(): String
}
