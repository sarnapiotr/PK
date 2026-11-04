#ifndef SM2026_RGB565_H_
#define SM2026_RGB565_H_

#include <SDL2/SDL.h>

void setRGB565(int xx, int yy, Uint8 r, Uint8 g, Uint8 b);
void setRGB565(int xx, int yy, Uint16 rgb565);
SDL_Color getRGB565(int xx, int yy);
Uint16 getRGB565_(int xx, int yy);

#endif  // SM2026_RGB565_H_