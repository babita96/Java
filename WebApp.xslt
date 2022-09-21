<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="utf-8" />
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />		
		</head>
		<body>
			<div class="container" style="margin-top: 20px;">
				<div class="jumbotron">
						<div class="card">
							<h3 align="center" class="card-header">
								RESTInterface
							</h3>
							<div class="card-body">
								<table align="center" border="1" class="table table-striped">
									<tr bgcolor="yellow">
										<th>Operation</th>
										<th>Argument(s)</th>
										<th>Return</th>
										<th>Exception</th>
									</tr>

									<xsl:for-each select="interface/abstract_method">
										<tr>
											<td>
												<xsl:value-of select="@name" />
											</td>
											<td>
												<xsl:for-each select="parameters">
													<xsl:for-each select="argument">
														<xsl:apply-templates select="text()" />
														:
														<xsl:apply-templates select="@type" />
														<xsl:if test="position() != last()">
															,
														</xsl:if>
													</xsl:for-each>
												</xsl:for-each>
											</td>
											<td>
												<xsl:value-of select="return" />
											</td>
											<td>
												<xsl:choose>
													<xsl:when test="throws">
														Yes
													</xsl:when>
													<xsl:otherwise>
														No
													</xsl:otherwise>
												</xsl:choose>
											</td>
										</tr>
									</xsl:for-each>
								</table>
							</div>
						</div>
					</div>
			</div>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
