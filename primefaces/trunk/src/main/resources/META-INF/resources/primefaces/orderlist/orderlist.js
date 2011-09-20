/**
 * PrimeFaces OrderList Widget
 */
PrimeFaces.widget.OrderList = function(id, cfg) {
    this.id = id;
    this.cfg = cfg;
    this.jqId = PrimeFaces.escapeClientId(this.id);
    this.jq = $(this.jqId);
    this.list = this.jq.find('.ui-orderlist-list'),
    this.items = this.list.children('.ui-orderlist-item');
    this.state = $(this.jqId + '_values');
    var _self = this;

    this.setupButtons();
    
    this.bindEvents();

    //Enable dnd
    this.list.sortable({
        revert: true,
        start: function(event, ui) {
            PrimeFaces.clearSelection();
        } 
        ,update: function(event, ui) {
            _self.onDragDrop(event, ui);
        }
    });
}

/**
 * Visuals
 */
PrimeFaces.widget.OrderList.prototype.bindEvents = function() {

    this.items.mouseover(function(e) {
        var element = $(this);

        if(!element.hasClass('ui-state-highlight'))
            $(this).addClass('ui-state-hover');
    })
    .mouseout(function(e) {
        var element = $(this);

        if(!element.hasClass('ui-state-highlight'))
            $(this).removeClass('ui-state-hover');
    })
    .mousedown(function(e) {
        var element = $(this);

        if(!e.metaKey) {
            element.removeClass('ui-state-hover').addClass('ui-state-highlight')
            .siblings('.ui-state-highlight').removeClass('ui-state-highlight');
        }
        else {
            if(element.hasClass('ui-state-highlight'))
                element.removeClass('ui-state-highlight');
            else
                element.removeClass('ui-state-hover').addClass('ui-state-highlight');
        }
    });
}

/**
 * Creates button controls using progressive enhancement
 */
PrimeFaces.widget.OrderList.prototype.setupButtons = function() {
    var _self = this;
    
    $(this.jq).find(' .ui-orderlist-controls .ui-orderlist-button-move-up').button({icons: {primary: "ui-icon-arrow-1-n"},text: (!this.cfg.iconOnly)}).click(function() {_self.moveUp(_self.sourceList);});
    $(this.jq).find(' .ui-orderlist-controls .ui-orderlist-button-move-top').button({icons: {primary: "ui-icon-arrowstop-1-n"},text: (!this.cfg.iconOnly)}).click(function() {_self.moveTop(_self.sourceList);});
    $(this.jq).find(' .ui-orderlist-controls .ui-orderlist-button-move-down').button({icons: {primary: "ui-icon-arrow-1-s"},text: (!this.cfg.iconOnly)}).click(function() {_self.moveDown(_self.sourceList);});
    $(this.jq).find(' .ui-orderlist-controls .ui-orderlist-button-move-bottom').button({icons: {primary: "ui-icon-arrowstop-1-s"},text: (!this.cfg.iconOnly)}).click(function() {_self.moveBottom(_self.sourceList);});
}

PrimeFaces.widget.OrderList.prototype.onDragDrop = function(event, ui) {
    ui.item.removeClass('ui-state-highlight');

    this.saveState();
}

PrimeFaces.widget.OrderList.prototype.saveState = function() {
    var values = [];
    
    this.list.children('li.ui-orderlist-item').each(function() {
        var item = $(this),
        itemValue = item.data('item-value');

        values.push(itemValue);
    });
    
    this.state.val(values.join(','));
}

    