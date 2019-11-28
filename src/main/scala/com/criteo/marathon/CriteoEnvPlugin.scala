package com.criteo.marathon

import mesosphere.marathon.plugin.plugin.PluginConfiguration
import mesosphere.marathon.plugin.task._
import mesosphere.marathon.plugin.{ApplicationSpec, PodSpec}
import org.apache.mesos.Protos
import org.apache.mesos.Protos._

class CriteoEnvPlugin extends RunSpecTaskProcessor with PluginConfiguration
{
  private[marathon] var envVariables = Map.empty[String, String]
  private[marathon] var labels = Map.empty[String, String]

  def initialize(marathonInfo: Map[String, Any], configuration: play.api.libs.json.JsObject): Unit = {
    envVariables = (configuration \ "env").as[Map[String, String]]
    labels = (configuration \ "labels").as[Map[String, String]]
  }

  override def taskInfo(appSpec: ApplicationSpec, builder: TaskInfo.Builder): Unit = {
    val envBuilder = builder.getCommand.getEnvironment.toBuilder
    envVariables.foreach {
      case (key, value) =>
        val envVariable = Protos.Environment.Variable.newBuilder()
        envVariable.setName(key)
        envVariable.setValue(value)
        envBuilder.addVariables(envVariable)
    }
    val commandBuilder = builder.getCommand.toBuilder
    commandBuilder.setEnvironment(envBuilder)
    builder.setCommand(commandBuilder)
    var labelBuilder = builder.getLabels.toBuilder
    labels.foreach {
      case (key, value) =>
        val label = Protos.Label.newBuilder()
        label.setKey(key)
        label.setValue(value)
        labelBuilder.addLabels(label)
    }
    builder.setLabels(labelBuilder)
  }

  override def taskGroup(podSpec: PodSpec, executor: ExecutorInfo.Builder,
    taskGroup: TaskGroupInfo.Builder): Unit = {
  }
}

