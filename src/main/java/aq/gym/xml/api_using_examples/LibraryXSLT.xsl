<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	exclude-result-prefixes="xs"
	version="2.0">
	<xsl:output method="html" />
	<xsl:template match="library">
		<html>
			<head>
				<title>
					<xsl:value-of select="/library/@lib_name" />
				</title>
			</head>
			<body>
				<table border="1">
					<tr>
						<th>Номер</th>
						<th>Название</th>
						<th>Автор</th>
						<th>Жанр</th>
					</tr>
					<xsl:apply-templates select="book">
						<xsl:sort select="@type" />
						<xsl:sort>
							<xsl:for-each select="author">
								<xsl:sort select="text()" />
							</xsl:for-each>
						</xsl:sort>
					</xsl:apply-templates>
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="book">
		<tr>
			<th>
				<xsl:value-of select="self::node()/@id" />
			</th>
			<th>
				<xsl:value-of select="child::title/text()" />
			</th>
			<th>
				<xsl:choose>
					<xsl:when test="count(author) = 1">
						<xsl:value-of select="child::author/text()" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:for-each select="child::author">
							<xsl:value-of select="text()" />
							<xsl:if
								test="position() != count(parent::node()/author)">
								<xsl:text>, </xsl:text>
							</xsl:if>
						</xsl:for-each>
					</xsl:otherwise>
				</xsl:choose>
			</th>
			<th>
				<xsl:value-of select="self::node()/@type" />
			</th>
		</tr>
	</xsl:template>
</xsl:stylesheet>