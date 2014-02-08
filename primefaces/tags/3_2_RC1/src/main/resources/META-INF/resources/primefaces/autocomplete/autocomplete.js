/**
 * PrimeFaces AutoComplete Widget
 */
PrimeFaces.widget.AutoComplete = PrimeFaces.widget.BaseWidget.extend({
    
    init: function(cfg) {
        this._super(cfg);
        
        this.panelId = this.jqId + '_panel';
        this.input = $(this.jqId + '_input');
        this.hinput = $(this.jqId + '_hinput');
        this.panel = this.jq.children(this.panelId);
        this.dropdown = this.jq.children('.ui-button');
        this.disabled = this.input.is(':disabled');
        this.active = true;
        this.cfg.pojo = this.hinput.length == 1;
        var _self = this;

        //options
        this.cfg.minLength = this.cfg.minLength != undefined ? this.cfg.minLength : 1;
        this.cfg.delay = this.cfg.delay != undefined ? this.cfg.delay : 300;

        if(this.cfg.multiple) {
            this.setupMultipleMode();

            this.multiItemContainer.data('primefaces-overlay-target', true).find('*').data('primefaces-overlay-target', true);
        } 
        else {
            //visuals
            PrimeFaces.skinInput(this.input);

            this.input.data('primefaces-overlay-target', true).find('*').data('primefaces-overlay-target', true);
            this.dropdown.data('primefaces-overlay-target', true).find('*').data('primefaces-overlay-target', true);
        }

        //core events
        this.bindStaticEvents();

        //client Behaviors
        if(this.cfg.behaviors) {
            PrimeFaces.attachBehaviors(this.input, this.cfg.behaviors);
        }

        //force selection
        if(this.cfg.forceSelection) {
            this.setupForceSelection();
        }

        //Panel management
        $(document.body).children(this.panelId).remove();
        this.panel.appendTo(document.body);

        //Hide overlay on resize
        var resizeNS = 'resize.' + this.id;
        $(window).unbind(resizeNS).bind(resizeNS, function() {
            if(_self.panel.is(':visible')) {
                _self.hide();
            }
        });

        //dialog support
        this.setupDialogSupport();
    },
    
    /**
     * Binds events for multiple selection mode
     */
    setupMultipleMode: function() {
        var _self = this;
        this.multiItemContainer = this.jq.children('ul');
        this.inputContainer = this.multiItemContainer.children('.ui-autocomplete-input-token');

        this.multiItemContainer.hover(function() {
                $(this).addClass('ui-state-hover');
            },
            function() {
                $(this).removeClass('ui-state-hover');
            }
        ).click(function() {
            _self.input.focus();
        });

        //delegate events to container
        this.input.focus(function() {
            _self.multiItemContainer.addClass('ui-state-focus');
        }).blur(function(e) {
            _self.multiItemContainer.removeClass('ui-state-focus');
        });

        //remove token
        $(this.jqId + ' li.ui-autocomplete-token .ui-autocomplete-token-icon').die().live('click', function(e) {
            _self.removeItem(e, $(this).parent());
        });
    },
    
    setupDialogSupport: function() {
        var dialog = this.jq.parents('.ui-dialog:first');

        if(dialog.length == 1) {
            this.panel.css('position', 'fixed');
        }
    },
    
    bindStaticEvents: function() {
        var _self = this;

        //bind keyup handler
        this.input.keyup(function(e) {
            var keyCode = $.ui.keyCode,
            key = e.which,
            shouldSearch = true;

            if(key == keyCode.UP 
                || key == keyCode.LEFT 
                || key == keyCode.DOWN 
                || key == keyCode.RIGHT 
                || key == keyCode.TAB 
                || key == keyCode.ENTER
                || key == keyCode.NUMPAD_ENTER) {
                shouldSearch = false;
            } 
            else if(_self.cfg.pojo && !_self.cfg.multiple) {
                _self.hinput.val($(this).val());
            }

            if(shouldSearch) {
                var value = _self.input.val();

                if(!value.length) {
                    _self.hide();
                }

                if(value.length >= _self.cfg.minLength) {

                    //Cancel the search request if user types within the timeout
                    if(_self.timeout) {
                        clearTimeout(_self.timeout);
                    }

                    _self.timeout = setTimeout(function() {
                        _self.search(value);
                    }, 
                    _self.cfg.delay);
                }
            }

        });

        //key events
        this.input.keydown(function(e) {
            if(_self.panel.is(':visible')) {
                var keyCode = $.ui.keyCode,
                currentItems = _self.panel.find('.ui-autocomplete-item'),
                highlightItem = _self.panel.find('.ui-autocomplete-item.ui-state-highlight');

                switch(e.which) {
                    case keyCode.UP:
                    case keyCode.LEFT:
                        var prev;
                        if(highlightItem.length > 0) {
                            prev = highlightItem.removeClass('ui-state-highlight').prev();
                            if(prev.length > 0){
                                prev.addClass('ui-state-highlight');
                                var diff = prev.offset().top - _self.panel.offset().top - prev.outerHeight(true) + prev.height();
                                if( diff < 0 )
                                    _self.panel.scrollTop( _self.panel.scrollTop() + diff);
                            }
                        } 

                        if(!prev || prev.length == 0) {
                            prev = currentItems.eq(currentItems.length - 1).addClass('ui-state-highlight');
                            _self.panel.scrollTop(prev.offset().top + prev.outerHeight(true) - _self.panel.offset().top - _self.panel.height());
                        }

                        e.preventDefault();
                        break;

                    case keyCode.DOWN:
                    case keyCode.RIGHT:
                        var next;
                        if(highlightItem.length > 0) {
                            next = highlightItem.removeClass('ui-state-highlight').next();
                            if(next.length > 0){
                                next.addClass('ui-state-highlight');
                                var diff = next.offset().top + next.outerHeight(true) - _self.panel.offset().top;
                                if( diff > _self.panel.height() )
                                    _self.panel.scrollTop(_self.panel.scrollTop() + (diff - _self.panel.height()));
                        }
                        } 

                        if(!next || next.length == 0) {
                            currentItems.eq(0).addClass('ui-state-highlight');
                            _self.panel.scrollTop(0);
                        }

                        e.preventDefault();
                        break;

                    case keyCode.ENTER:
                    case keyCode.NUMPAD_ENTER:
                        highlightItem.click();

                        e.preventDefault();
                        break;

                    case keyCode.ALT: 
                    case 224:
                        break;

                    case keyCode.TAB:
                        _self.hide();
                        break;
                }
            }

        });

        //dropdown
        this.dropdown.mouseover(function() {
            if(!_self.disabled) {
                $(this).addClass('ui-state-hover');
            }
        }).mouseout(function() {
            if(!_self.disabled) {
                $(this).removeClass('ui-state-hover');
            }
        }).mousedown(function() {
            if(!_self.disabled && _self.active) {
                $(this).addClass('ui-state-active');
            }
        }).mouseup(function() {
            if(!_self.disabled && _self.active) {
                $(this).removeClass('ui-state-active');

                _self.search('');
                _self.input.focus();
            }
        });

        //hide overlay when outside is clicked
        var offset;
        $(document.body).bind('mousedown.ui-autocomplete', function (e) {
            if(_self.panel.is(":hidden")) {
                return;
            }
            offset = _self.panel.offset();
            if(e.target === _self.input.get(0)) {
                return;
            }
            if (e.pageX < offset.left ||
                e.pageX > offset.left + _self.panel.width() ||
                e.pageY < offset.top ||
                e.pageY > offset.top + _self.panel.height()) {
                _self.hide();
            }
        });
    },
    
    bindDynamicEvents: function() {
        var _self = this,
        items = this.panel.find('.ui-autocomplete-item');

        //visuals and click handler for items
        items.bind('mouseover', function() {
            $(this).addClass('ui-state-highlight');
        })
        .bind('mouseout', function() {
            $(this).removeClass('ui-state-highlight');
        })
        .bind('click', function(event) {
            var item = $(this),
            itemValue = item.attr('data-item-value');

            if(_self.cfg.multiple) {
                var itemDisplayMarkup = '<li data-token-value="' + item.attr('data-item-value') + '"class="ui-autocomplete-token ui-state-active ui-corner-all ui-helper-hidden">';
                itemDisplayMarkup += '<span class="ui-autocomplete-token-icon ui-icon ui-icon-close" />';
                itemDisplayMarkup += '<span class="ui-autocomplete-token-label">' + item.attr('data-item-label') + '</span></li>';

                _self.inputContainer.before(itemDisplayMarkup);
                _self.multiItemContainer.children('.ui-helper-hidden').fadeIn();
                _self.input.val('').focus();

                _self.hinput.append('<option value="' + itemValue + '" selected="selected"></option>');
            }
            else {
                _self.input.val(item.attr('data-item-label'));

                if(_self.cfg.pojo) {
                    _self.hinput.val(itemValue);            
                }
            }

            _self.invokeItemSelectBehavior(event, itemValue);

            _self.hide();
        });
    },
    
    search: function(value) {
        if(!this.active) {
            return;
        }

        var _self = this;

        //start callback
        if(this.cfg.onstart) {
            this.cfg.onstart.call(this, value);
        }
        
        var options = {
            source: this.id,
            update: this.id,
            formId: this.cfg.formId,
            onsuccess: function(responseXML) {
                var xmlDoc = $(responseXML.documentElement),
                updates = xmlDoc.find("update");

                for(var i=0; i < updates.length; i++) {
                    var update = updates.eq(i),
                    id = update.attr('id'),
                    data = update.text();

                    if(id == _self.id) {
                        _self.panel.html(data);
                        _self.bindDynamicEvents();

                        var items = _self.panel.find('.ui-autocomplete-item');

                        if(items.length > 0) {
                            //highlight first item
                            items.eq(0).addClass('ui-state-highlight');

                            //highlight query string
                            if(_self.panel.children().is('ul')) {
                                items.each(function() {
                                    var item = $(this),
                                    text = item.text(),
                                    re = new RegExp(value, 'gi'),
                                    highlighedText = text.replace(re, '<span class="ui-autocomplete-query">$&</span>');
                                    
                                    item.html(highlighedText);
                                });
                            }

                            if(_self.cfg.forceSelection) {
                                _self.cachedResults = [];
                                items.each(function(i, item) {
                                    _self.cachedResults.push($(item).attr('data-item-label'));
                                });
                            }

                            if(_self.panel.is(':hidden')) {
                                _self.show();
                            } else {
                                _self.alignPanel(); //with new items
                            }

                            //adjust height
                            _self.panel.css('height', '');
                            if(_self.cfg.scrollHeight && _self.panel.height() > _self.cfg.scrollHeight) {
                                _self.panel.css('height', _self.cfg.scrollHeight + 'px');
                            }
                        }
                        else {
                            _self.panel.hide();
                        }
                    } 
                    else {
                        PrimeFaces.ajax.AjaxUtils.updateElement.call(this, id, data);
                    }
                }

                PrimeFaces.ajax.AjaxUtils.handleResponse.call(this, xmlDoc);

                return true;
            }
        };

        //complete callback
        if(this.cfg.oncomplete) {
            options.oncomplete = this.cfg.oncomplete;
        }
        
        //process
        options.process = this.cfg.process ? this.id + ' ' + this.cfg.process : this.id;

        if(this.cfg.global === false) {
            options.global = false;
        }

        var params = {};
        params[this.id + '_query'] = encodeURIComponent(value);

        options.params = params;

        PrimeFaces.ajax.AjaxRequest(options);
    },
    
    show: function() {
        this.alignPanel();

        this.panel.css('z-index', ++PrimeFaces.zindex);

        if($.browser.msie && /^[6,7]\.[0-9]+/.test($.browser.version)) {
            this.panel.parent().css('z-index', PrimeFaces.zindex - 1);
        }

        if(this.cfg.effect)
            this.panel.show(this.cfg.effect, {}, this.cfg.effectDuration);
        else
            this.panel.show();
    },
    
    hide: function() {
        if($.browser.msie && /^[6,7]\.[0-9]+/.test($.browser.version)) {
            this.panel.parent().css('z-index', '');
        }

        this.panel.css('z-index', '').hide();
    },
    
    invokeItemSelectBehavior: function(event, itemValue) {
        if(this.cfg.behaviors) {
            var itemSelectBehavior = this.cfg.behaviors['itemSelect'];

            if(itemSelectBehavior) {
                var ext = {
                    params : {}
                };
                ext.params[this.id + "_itemSelect"] = itemValue;

                itemSelectBehavior.call(this, event, ext);
            }
        }
    },
    
    invokeItemUnselectBehavior: function(event, itemValue) {
        if(this.cfg.behaviors) {
            var itemUnselectBehavior = this.cfg.behaviors['itemUnselect'];

            if(itemUnselectBehavior) {
                var ext = {
                    params : {}
                };
                ext.params[this.id + "_itemUnselect"] = itemValue;

                itemUnselectBehavior.call(this, event, ext);
            }
        }
    },
    
    removeItem: function(event, item) {
        var itemValue = item.attr('data-token-value'),
        _self = this;
        
        //remove from options
        this.hinput.children('option').filter('[value="' + itemValue + '"]').remove();
        
        //remove from items
        item.fadeOut('fast', function() {
            var token = $(this);

            token.remove();

            _self.invokeItemUnselectBehavior(event, itemValue);
        });
    },
    
    setupForceSelection: function() {
        this.cachedResults = [this.input.val()];
        var _self = this;

        this.input.blur(function() {
            var value = $(this).val(),
            valid = false;

            for(var i = 0; i < _self.cachedResults.length; i++) {
                if(_self.cachedResults[i] == value) {
                    valid = true;
                    break;
                }
            }

            if(!valid) {
                $(this).val('');
            }
        });
    },
    
    disable: function() {
        this.disabled = true;
        this.input.addClass('ui-state-disabled').attr('disabled', 'disabled');
    },
    
    enable: function() {
        this.disabled = false;
        this.input.removeClass('ui-state-disabled').removeAttr('disabled');
    },
    
    close: function() {
        this.hide();
    },
    
    deactivate: function() {
        this.active = false;
    },
    
    activate: function() {
        this.active = true;
    },
    
    alignPanel: function() {
        var fixedPosition = this.panel.css('position') == 'fixed',
        win = $(window),
        positionOffset = fixedPosition ? '-' + win.scrollLeft() + ' -' + win.scrollTop() : null,
        panelWidth = null;

        if(this.cfg.multiple) {
            panelWidth = this.multiItemContainer.innerWidth() - (this.input.position().left - this.multiItemContainer.position().left);
        }
        else {
            panelWidth = this.input.innerWidth();
        }

        this.panel.css({
                        left:'',
                        top:'',
                        width: panelWidth
                })
                .position({
                    my: 'left top'
                    ,at: 'left bottom'
                    ,of: this.input,
                    offset : positionOffset
                });
    }
    
});