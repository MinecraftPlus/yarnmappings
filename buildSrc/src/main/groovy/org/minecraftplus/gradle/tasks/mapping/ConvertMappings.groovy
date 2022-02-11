package org.minecraftplus.gradle.tasks.mapping

import net.minecraftforge.srgutils.IMappingFile
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

class ConvertMappings extends DefaultTask {

    @InputFile File intermediary
    @InputFile File tiny
    @OutputFile File output

    @TaskAction
    def exec() {
        logger.lifecycle(":loading tiny mapping {}", tiny)
        def mapping = IMappingFile.load(tiny).reverse()

        logger.lifecycle(":chaining tiny with intermediary {}", intermediary)
        def chained = mapping.chain(IMappingFile.load(intermediary).reverse())

        logger.lifecycle(":saving converted mapping to {}", intermediary)
        chained.write(output.toPath(), IMappingFile.Format.TINY, true)
    }
}
