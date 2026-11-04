#ifndef SM2026_RGB555_H_
#define SM2026_RGB555_H_

#include <SDL2/SDL.h>

void setRGB555(int xx, int yy, Uint8 r, Uint8 g, Uint8 b);
void setRGB555(int xx, int yy, Uint16 rgb555);
SDL_Color getRGB555(int xx, int yy);
Uint16 getRGB555_(int xx, int yy);

#endif  // SM2026_RGB555_H_