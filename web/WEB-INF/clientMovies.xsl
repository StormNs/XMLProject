<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : clientMovies.xsl
    Created on : July 6, 2017, 2:10 AM
    Author     : USER
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" version="1.0"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:element name="movies">
            <xsl:for-each select="movies/movie">
                <xsl:element name="movie">
                    <xsl:element name="id">
                        <xsl:value-of select="id"/>
                    </xsl:element>
                    <xsl:element name="name">
                        <xsl:value-of select="name"/>
                    </xsl:element>
                    <xsl:if test="alternateName != ''">
                        <xsl:element name="alternateName">
                            <xsl:value-of select="alternateName"/>
                        </xsl:element>
                    </xsl:if>
                    <xsl:if test="description != ''">
                        <xsl:element name="description">
                            <xsl:value-of select="description"/>
                        </xsl:element>
                    </xsl:if>
                    <xsl:if test="imageCover != ''">
                        <xsl:element name="imageCover">
                            <xsl:value-of select="imageCover"/>
                        </xsl:element>
                    </xsl:if>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
