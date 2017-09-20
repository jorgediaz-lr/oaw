<%@ include file="/common/taglibs.jsp"%>
<%@page import="es.inteco.common.Constants"%>
<html:xhtml />
<html:javascript formName="SemillaObservatorioForm" />

<link rel="stylesheet" href="/oaw/js/jqgrid/css/ui.jqgrid.css">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/oaw/js/jqgrid/jquery.jqgrid.src.js"></script>
<script src="/oaw/js/jqgrid/i18n/grid.locale-es.js"
	type="text/javascript"></script>

<script src="/oaw/js/gridSemillasResultado.js" type="text/javascript"></script>


<!--  JQ GRID   -->
<script>
	//Buscador
	function buscar() {
		reloadGrid('/oaw/secure/JsonSemillasObservatorio.do?action=buscar&'
				+ $('#SemillaSearchForm').serialize());
	}

	$(window)
			.on(
					'load',
					function() {

						var $jq = $.noConflict();

						var lastUrl;

						//Primera carga del grid el grid
						$jq(document)
								.ready(
										function() {
											reloadGrid('JsonResultadoObservatorio.do?action=resultados&id_observatorio='
													+ $(
															'[name=id_observatorio]')
															.val()
													+ '&idExObs='
													+ $('[name=idExObs]').val()
													+ '&idCartucho='
													+ $('[name=idCartucho]')
															.val());
										});

					});

	var dialog;

	var windowWidth = $(window).width() * 0.8;
	var windowHeight = $(window).height() * 0.8;

	function dialogoEditarSemilla(rowid) {

		window.scrollTo(0, 0);

		$('#exitosNuevaSemillaMD').hide();
		$('#erroresNuevaSemillaMD').hide();

		dialog = $("#dialogoEditarSemilla").dialog({
			height : windowHeight,
			width : windowWidth,
			modal : true,
			buttons : {
				"Guardar" : function() {
					editarNuevaSemilla();
				},
				"Cancelar" : function() {
					dialog.dialog("close");
				}
			},
			open : function() {

				//Pasamos la fila
				cargarSelect($('#grid').getLocalRow(rowid));

			},
			close : function() {
				$('#nuevaSemillaMultidependencia')[0].reset();
				$('#selectDependenciasNuevaSemillaSeleccionadas').html('');
			}
		});

		//Cargamos los datos

		$('#nuevaSemillaMultidependencia input[name=id]').val(
				$('#grid').getLocalRow(rowid).id);
		$('#nuevaSemillaMultidependencia input[name=nombre]').val(
				$('#grid').getLocalRow(rowid).nombre);
		$('#nuevaSemillaMultidependencia input[name=nombreAntiguo]').val(
				$('#grid').getLocalRow(rowid).nombre);
		$('#nuevaSemillaMultidependencia input[name=acronimo]').val(
				$('#grid').getLocalRow(rowid).acronimo);
		$('#nuevaSemillaMultidependencia textarea[name=listaUrlsString]').val(
				$('#grid').getLocalRow(rowid).listaUrls.toString().replace(
						/\,/g, '\r\n'));
		$(
				'#nuevaSemillaMultidependencia  select[name=activa] option[value='
						+ $('#grid').getLocalRow(rowid).activa + ']').attr(
				'selected', 'selected');
		$(
				'#nuevaSemillaMultidependencia  select[name=directorio] option[value='
						+ $('#grid').getLocalRow(rowid).inDirectory + ']')
				.attr('selected', 'selected');

	}

	//Guardar la nueva semilla

	function editarNuevaSemilla() {
		$('#exitosNuevaSemillaMD').hide();
		$('#exitosNuevaSemillaMD').html("");
		$('#erroresNuevaSemillaMD').hide();
		$('#erroresNuevaSemillaMD').html("");

		var guardado = $.ajax({
			url : '/oaw/secure/JsonSemillasObservatorio.do?action=update',
			data : $('#nuevaSemillaMultidependencia').serialize(),
			method : 'POST',
			traditional : true,
		}).success(
				function(response) {
					$('#exitosNuevaSemillaMD').addClass('alert alert-success');
					$('#exitosNuevaSemillaMD').append("<ul>");

					$.each(JSON.parse(response), function(index, value) {
						$('#exitosNuevaSemillaMD').append(
								'<li>' + value.message + '</li>');
					});

					$('#exitosNuevaSemillaMD').append("</ul>");
					$('#exitosNuevaSemillaMD').show();
					dialog.dialog("close");
					reloadGrid(lastUrl);

				}).error(
				function(response) {
					$('#erroresNuevaSemillaMD').addClass('alert alert-danger');
					$('#erroresNuevaSemillaMD').append("<ul>");

					$.each(JSON.parse(response.responseText), function(index,
							value) {
						$('#erroresNuevaSemillaMD').append(
								'<li>' + value.message + '</li>');
					});

					$('#erroresNuevaSemillaMD').append("</ul>");
					$('#erroresNuevaSemillaMD').show();

				}

		);

		return guardado;
	}
</script>

<link rel="stylesheet" href="/oaw/css/jqgrid.semillas.css">


<bean:define id="idCartridgeMalware">
	<inteco:properties key="cartridge.malware.id" file="crawler.properties" />
</bean:define>
<bean:define id="idCartridgeLenox">
	<inteco:properties key="cartridge.lenox.id" file="crawler.properties" />
</bean:define>
<bean:define id="idCartridgeIntav">
	<inteco:properties key="cartridge.intav.id" file="crawler.properties" />
</bean:define>
<bean:define id="idCartridgeMultilanguage">
	<inteco:properties key="cartridge.multilanguage.id"
		file="crawler.properties" />
</bean:define>

<bean:parameter name="<%=Constants.ID_OBSERVATORIO%>"
	id="idObservatorio" />
<bean:parameter name="<%=Constants.ID_EX_OBS%>" id="idExObs" />
<bean:parameter name="<%=Constants.ID_CARTUCHO%>" id="idCartucho" />

<div id="dialogoEditarSemilla" style="display: none">
	<jsp:include page="./observatorio_nuevaSemilla_multidependencia.jsp"></jsp:include>

</div>


<div id="main">

	<div id="container_menu_izq">
		<jsp:include page="menu.jsp" />
	</div>

	<div id="container_der">
		<div id="migas">
			<p class="sr-only">
				<bean:message key="ubicacion.usuario" />
			</p>
			<ol class="breadcrumb">
				<li><html:link forward="observatoryMenu">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
						<bean:message key="migas.observatorio" />
					</html:link></li>
				<li><html:link forward="resultadosPrimariosObservatorio"
						paramName="idObservatorio"
						paramId="<%=Constants.ID_OBSERVATORIO%>">
						<bean:message key="migas.indice.observatorios.realizados.lista" />
					</html:link></li>
				<li class="active"><bean:message
						key="migas.resultado.observatorio" /></li>
			</ol>
		</div>

		<div id="cajaformularios">
			<h2>
				<bean:message key="gestion.resultados.observatorio" />
			</h2>

			<div id="exitosNuevaSemillaMD" style="display: none"></div>

			<html:form action="/secure/ResultadosObservatorio.do" method="get"
				styleClass="formulario form-horizontal">
				<input type="hidden" name="<%=Constants.ACTION%>"
					value="<%=Constants.GET_SEEDS%>" />
				<input type="hidden" name="<%=Constants.ID_OBSERVATORIO%>"
					value="<bean:write name="idObservatorio"/>" />
				<input type="hidden" name="<%=Constants.ID_EX_OBS%>"
					value="<bean:write name="idExObs"/>" />
				<input type="hidden" name="<%=Constants.ID_CARTUCHO%>"
					value="<bean:write name="<%=Constants.ID_CARTUCHO%>"/>" />
				<fieldset>
					<legend>Buscador</legend>
					<jsp:include page="/common/crawler_messages.jsp" />
					<div class="formItem">
						<label for="nombre" class="control-label"><strong
							class="labelVisu"><bean:message
									key="nueva.semilla.observatorio.nombre" /></strong></label>
						<html:text styleClass="texto form-control" styleId="nombre"
							property="nombre" />
					</div>
					<div class="formItem">
						<label for="listaUrlsString" class="control-label"><strong
							class="labelVisu"><bean:message
									key="nueva.semilla.observatorio.url" /></strong></label>
						<html:text styleClass="texto form-control"
							styleId="listaUrlsString" property="listaUrlsString" />
					</div>
					<div class="formButton">
						<button type="submit" class="btn btn-default btn-lg">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							<bean:message key="boton.buscar" />
						</button>
					</div>
				</fieldset>
			</html:form>

			<!-- Grid -->
			<table id="grid">
			</table>



			<p id="paginador"></p>


		</div>
		<!-- fin cajaformularios -->
	</div>
</div>