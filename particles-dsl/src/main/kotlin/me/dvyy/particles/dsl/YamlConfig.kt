package me.dvyy.particles.dsl

import com.charleskorn.kaml.*
import java.io.InputStream

class YamlConfig(input: InputStream) {
    val yaml = Yaml.default
    val node: YamlMap = yaml.parseToYamlNode(input).yamlMap

    private fun get(key: String): YamlNode = key.split(".").fold(node) { acc: YamlNode, stringPart ->
        acc.yamlMap.get<YamlNode>(stringPart) ?: error("Key $key not found in config")
    }

    fun propertyOrNull(key: String): String? = runCatching { property(key) }.getOrNull()
    fun property(key: String): String = get(key).yamlScalar.content
}
