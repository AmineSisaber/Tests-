Chart.pluginService.register({
  beforeDraw: function (chart) {
    if (chart.config.options.elements.center) {
      //Get ctx from string
      var ctxTop = chart.chart.ctx;
      
      //Get options from the center object in options
      var centerConfig = chart.config.options.elements.center;
      var fontStyle = centerConfig.fontStyle || 'Arial';
      var txt = centerConfig.textTop;
//      var color = centerConfig.color || '#000';
      var sidePadding = centerConfig.sidePadding || 20;
      var sidePaddingCalculated = (sidePadding/100) * (chart.innerRadius * 2)
      //Start with a base font of 30px
      ctxTop.font = "20px " + fontStyle;

      //Get the width of the string and also the width of the element minus 10 to give it 5px side padding
      var stringWidth = ctxTop.measureText(txt).width;
      var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;

      // Find out how much the font can grow in width.
      var widthRatio = elementWidth / stringWidth;
      var newFontSize = Math.floor(20 * widthRatio);
      var elementHeight = (chart.innerRadius * 2);

      // Pick a new font size so it will not be larger than the height of label.
      var fontSizeToUse = Math.min(newFontSize, elementHeight);

      //Set font settings to draw it correctly.
      ctxTop.textAlign = 'center';
      ctxTop.textBaseline = 'middle';
      var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
      var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);
      ctxTop.font = 'bold ' + fontSizeToUse+"px " + fontStyle;
      ctxTop.fillStyle = centerConfig.colorTop || '#e04580';
      ctxTop.shadowColor = centerConfig.colorTop || '#e04580';
      ctxTop.strokeStyle = centerConfig.colorTop || '#e04580';
      ctxTop.lineJoin="bevel"; //Experiment with "bevel" & "round" for the effect you want!
//      ctxTop.fillText(txt, centerX, centerY);
      ctxTop.fillText(txt, centerX, centerY - (fontSizeToUse/2) + 5);
//      
//      if(centerConfig.text) {
//	      var ctx = chart.chart.ctx;
//	      ctx.textAlign = 'center';
//	      ctx.textBaseline = 'middle';
//	      ctx.font = fontSizeToUse+"px " + fontStyle;
//	      ctx.fillStyle = centerConfig.color || '#000';
//	      ctx.fillText(centerConfig.text, centerX, centerY);
//      }
//      if(centerConfig.textBottom) {
//	      var ctx = chart.chart.ctx;
//	      ctx.textAlign = 'center';
//	      ctx.textBaseline = 'middle';
//	      ctx.font = (fontSizeToUse/2) +"px " + fontStyle;
//	      ctx.fillStyle = centerConfig.colorBottom || '#000';
//	      ctx.fillText(centerConfig.textBottom, centerX, centerY + (fontSizeToUse/2) + 2);
//      }
    }
  }
});